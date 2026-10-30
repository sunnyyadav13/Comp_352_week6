import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.spi.ToolProvider;

/**
 * Lab helper. Run from the project folder:
 *
 *   java tools/Check.java docs oop     count missing or broken Javadoc in oop/
 *   java tools/Check.java run oop      compile and run oop/Main.java
 *   java tools/Check.java site oop     build a documentation website from the Javadoc in docs-site/oop
 *
 * Use dsa instead of oop for the DSA track.
 */
public class Check {

    public static void main(String[] args) throws Exception {
        if (args.length != 2 || !List.of("docs", "run", "site").contains(args[0])) {
            System.out.println("usage: java tools/Check.java docs|run|site oop|dsa");
            System.exit(2);
        }
        Path track = Path.of(args[1]);
        if (!Files.isDirectory(track)) {
            System.out.println("No folder named " + track + ". Run this from the project folder.");
            System.exit(2);
        }
        switch (args[0]) {
            case "docs" -> docs(track);
            case "site" -> site(track);
            default -> run(track);
        }
    }

    private static List<String> sources(Path track, boolean includeMain) throws IOException {
        List<String> files = new ArrayList<>();
        try (var stream = Files.list(track)) {
            stream.filter(p -> p.toString().endsWith(".java"))
                  .filter(p -> includeMain || !p.getFileName().toString().equals("Main.java"))
                  .sorted()
                  .forEach(p -> files.add(p.toString()));
        }
        return files;
    }

    /** Runs the JDK's javadoc in strict mode and counts problems per file. */
    private static void docs(Path track) throws IOException {
        ToolProvider javadoc = ToolProvider.findFirst("javadoc")
                .orElseThrow(() -> new IllegalStateException("javadoc not found: install a JDK, not just a JRE"));
        Path out = Files.createTempDirectory("doccheck");
        List<String> args = new ArrayList<>(List.of("-Xdoclint:all", "-quiet", "-encoding", "UTF-8", "-d", out.toString()));
        args.addAll(sources(track, false));
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        PrintWriter w = new PrintWriter(buf, true, StandardCharsets.UTF_8);
        javadoc.run(w, w, args.toArray(new String[0]));
        Pattern p = Pattern.compile("(\\w+\\.java):(\\d+): (warning|error): (.*)");
        Map<String, Integer> perFile = new TreeMap<>();
        int total = 0;
        for (String line : buf.toString(StandardCharsets.UTF_8).split("\\R")) {
            Matcher m = p.matcher(line);
            if (m.find()) {
                perFile.merge(m.group(1), 1, Integer::sum);
                total++;
            }
        }
        System.out.println("Javadoc problems in " + track + "/:");
        if (perFile.isEmpty()) {
            System.out.println("  none");
        }
        perFile.forEach((file, n) -> System.out.printf("  %2d  %s%n", n, file));
        System.out.println();
        System.out.println("TOTAL: " + total);
    }

    /** Prints every changed line in the track that is not a comment. */
    /** Builds HTML reference pages from the Javadoc, like the official Java API documentation. */
    private static void site(Path track) throws IOException {
        ToolProvider javadoc = ToolProvider.findFirst("javadoc")
                .orElseThrow(() -> new IllegalStateException("javadoc not found: install a JDK, not just a JRE"));
        Path out = Path.of("docs-site", track.getFileName().toString());
        Files.createDirectories(out);
        String title = track.getFileName() + " track documentation";
        List<String> args = new ArrayList<>(List.of("-Xdoclint:none", "-quiet", "-encoding", "UTF-8", "-d", out.toString(),
                "-windowtitle", title, "-doctitle", title, "-notimestamp"));
        args.addAll(sources(track, false));
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        PrintWriter w = new PrintWriter(buf, true, StandardCharsets.UTF_8);
        if (javadoc.run(w, w, args.toArray(new String[0])) != 0) {
            System.out.println(buf.toString(StandardCharsets.UTF_8));
            System.exit(1);
        }
        System.out.println("Documentation website created.");
        System.out.println("Open this file in your browser: " + out.resolve("index.html").toAbsolutePath());
    }

    /** Compiles the track and runs its Main class. */
    private static void run(Path track) throws Exception {
        Path out = Path.of("build", track.getFileName().toString());
        Files.createDirectories(out);
        ToolProvider javac = ToolProvider.findFirst("javac").orElseThrow();
        List<String> args = new ArrayList<>(List.of("-d", out.toString()));
        args.addAll(sources(track, true));
        if (javac.run(System.out, System.err, args.toArray(new String[0])) != 0) {
            System.exit(1);
        }
        String java = ProcessHandle.current().info().command().orElse("java");
        Process p = new ProcessBuilder(java, "-cp", out.toString(), "Main").inheritIO().start();
        System.exit(p.waitFor());
    }
}
