---
name: javadoc
description: Write Javadoc for one Java file without changing any code
argument-hint: The file to document, for example #file:Recursion.java
agent: agent
---
Add Javadoc to every public class, constructor, method and protected field in the file
I give you. Follow the repository's custom instructions.

For each method:
- Explain what it does in one or two simple sentences.
- Add @param, @return and @throws tags.
- Say what happens with bad input.
- For recursive methods, name the base case and give the time complexity.

Do not change any code. When you are done, list anything you marked TODO(verify).
