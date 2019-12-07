# Note: This project has just been started and it merely compiles...

## *But in case you are interested, here is a brief presentation*

# j-YATL-on
![](https://travis-ci.org/lintense/jYATLon.svg?branch=master) [![codecov](https://codecov.io/gh/lintense/jYATLon/branch/master/graph/badge.svg)](https://codecov.io/gh/lintense/jYATLon)  
![Yet Another Template Language](https://raw.githubusercontent.com/lintense/jYATLon/master/img/logo.png)
## *Yet Another Template Language...*

### Why YATL?
Most current template languages are procedural, so they look like code, not like template... So they are (at times) hard to understand and need to be debugged, just like code!
What about a new *declarative* templating language build from the ground up to look just like a *real* template language?
Doing so would provide the following benefits:

- **Clarity:** No `${#if#elseif#endif}` gibberish.
- **Java 8 compatibility:** To accomodate existing projects.
- **Light & simple:** Easy to understand, easy to adapt!
- **Performance**... Whenever possible!
- **No dependency**: Small footprint.

### <a id="running"></a>Running the program
- Create a template file containing the following text: `Hello {{$}}!`
- Execute the following *java* code to create a context and invoke the engine:
```java
YATL yatl = YATL.getTemplate("filename");
Object root = "World"; // This is the root context
Writer writer = new StringWriter();
yatl.merge(root, writer); // The same as Velocity!
```
### Tutorial
- YATL is intended to be the simplest templating language possible. This is why it does this one thing, hopefully well!
- As a general rule, [values](#value), [commands](#command) and [comments](#comment) begin and end on the same line.
- Also, be aware that spaces are not allowed inside [value expressions](#value), [commands](#command) and [paths](#path).

#### <a id="root"></a>Root context
- The root context aka `$` refers to the object that is provided when launching the template engine. (See [running the program](#running) above)
- It is recommended that you have full control over the root object so you can implement any special formating services that you may need. YATL will not implement complex computations...

#### <a id="value"></a>Value
- A value is an expression enclosed in **double braces** `{{` ... `}}`. The so called [mustache](https://mustache.github.io/)!
- It can be a constant: `{{'any text'}}` or `{{"I'm a text!"}}`
- The result of a value expression is always inserted where it is declared.
- A value expression always starts with any of: the [root context](#root), a [path](#path) or an [alias](#alias). From there, it is possible to apply any public methods that is valid for this object and so on. For exemple you can do `{{$.toString}}` or `{{$.toString.substring(1,4)}}`.
- You can invoke public accessors with `{{$.getSize()}}` or `{{$.getSize}}` or even `{{$.size}}` as you prefer.
- You can use a Map key to access one of its value `{{Map.key}}`.
- If you want to insert any conditional text arround a value, for example when it's be empty or when it returns a collection, then give it an [alias](#alias) and enclose it in a [block](#block).
- A value expression **MUST NOT** contain spaces. Be sure you remember cause we will not tell you again!

##### Conditional output
- A value can be output conditionnaly by including a test `{{if $.test 'This is true!'}}`. 
- To inverse the result of the test, use `!` as in `{{if !$.test 'Oops! Sorry...'}}`.
- You can check if 2 values are equal `{{if $.v1 == $v2 $.v1:ALIAS}}` or not equal `{{if $.v1 != $v2 $.v2:ALIAS}}`.

##### Collection
- In case the [value](#value) is a `Collection` it can be usefull to know about the *index of* one element or the *size of* the `Collection` itself.
- The `indexOf` function returns the index of the current [value](#value) starting by `1`: `{{indexOf($.val)}}`.
 - The `sizeOf` function returns the number of [values](#value) that are visible for the {{alias}}(#alias): `{{sizeOf($.val:ALIAS)}}`.
- `Collection` functions may be used inside a condition: `{{if indexOf($.val) == 1 'First:'}}`.

#### <a id="alias"></a>Alias
- You may assign an alias to any part of the expression of a [value](#value). For exemple `{{$:ROOT.toString:ROOT_AGAIN.substring(1,4):PART_OF_ROOT}}`.
- Aliases **MUST** start with a letter or an underscore but they can also contain numbers: `[_A-Za-z][_A-Za-z0-9]*`.
- If any part of a [value](#value) expression is `null` or empty `""` then no text will be inserted. If you intend to provide a default text in those cases, then you **MUST** provide the [value](#value) with an alias and put it into a [block](#block). 

#### <a id="block"></a>Block
- A block is delimited by controls that are enclosed into **single braces** `{` `}`.
- A block is always associated to a single [alias](#alias). Its role is to control the visibilty of the text that surrounds the [value](#value) associated with its [alias](#alias).
- A block always begins by `{begin ALIAS}` and always ends by `{end ALIAS}`.
```javascript
{begin ALIAS} // The most simple block!
    {{$.val:ALIAS}}
{end ALIAS}
```

##### Empty value
- You may include a `{empty ALIAS}` control followed by the text you want to appear when the [value](#value) is empty.
```javascript
{begin ALIAS}
    x = {{$.val:ALIAS}} // Value Expression: always under {begin}
{empty ALIAS}
    x = null; // Text to appear if the Value is empty
{end ALIAS}
```
##### Collection value
- When the associated [value](#value) is a `Collection`, you can optionnaly use any of the following controls **always in this order**: `{before ALIAS}`, `{between ALIAS}`, `{after ALIAS}`, `{empty ALIAS}`.
- All associated [alias](#alias) expressions **MUST** be declared **directly** under the `{begin ALIAS}` control.
```javascript
{begin ALIAS}
x = List( // Text to appear before the whole value. Assumed to be a Collection here...
{{ALIAS}} // Value Expression: always directly under {begin} 
{before ALIAS}
" // Text to appear before each Value
{between ALIAS}
", // Text to appear between each Value
{after ALIAS}
"); // Text to appear after the whole Collection
{empty ALIAS}
x = null; // Text to appear if the Collection is empty
{end ALIAS}
```
- The previous example (if you remove the java comments) would return `x = List("a","b");` or `x = null;` in case there is nothing to display.

##### Short notation
- When the `before, between, after` sequences are short, you may use an alternative notation such as `{begin ALIAS |"|",|");}` for clarity. 
- The first character found after the [alias](#alias) **MUST** be repeated exactly 3 times in order to be correctly interpreted as the separator. So you can use any single character that you want for that nmatter.
- Remember that all control blocks are single lined.

##### Block imbrication
- Blocks can imbricated but not interlocked.
```java
{begin 1}
    {begin 2}{end 2} // This IS valid!
    {begin 3}
{empty 1}
    {end 3} // This is NOT valid!
{end 1}
```

##### <a id="matching"></a>Alias matching
- The same [alias](#alias) may be repeated among many [values](#value) and [paths](#path) so they can be matched together. In this case, all of these [aliases](#alias) **MUST** exist and be equal for the corresponding [value](#value) to be visible.
- It is possible to have optional aliases `{{$.value:?OPTIONAL_ALIAS:ALTERNATE_ALIAS}}` that can be `null` when the others are not. Optional aliases **MUST** have an alternate alias since they can be empty.
```javascript
{begin ALIAS}
    {{$.val:ALIAS}}
    {{ALIAS}}
    {{$.name:ALIAS}}
{end ALIAS}
```
- The previous example will write 3 times the same [value](#value) or nothing if the [values](#value) are not matching.

#### <a id="path"></a>Path Block
- Basically, a path block is a block that is defined outside of where it is actually inserted.
- A path block always start with a header such as `=== CLASS ===` where *CLASS* is the *java class* of the context of that block.
- The calling context class path may be added to form a sequence `=== CALLER/CLASS ===` so it can be referenced in the path block. This path is absolute and can only be called from the [root](#root) context.
- Use the any path syntax `=== .../CLASS ===` to allow the path block to be called from any context having a given class.
- The path name **MUST NOT** contain spaces.

##### Calling 
- To insert a path block, use the `call` operator inside a [value](#value) `{{call PATH $.val}}`. Remember that the path **MUST** match the actual class or interface returned by the [value](#value) expression.
- A path block can also be conditionnaly inserted `{{if $.test call PATH $.val}}`.
- It is possible to use [alias](#alias) to discriminate among multiple similar paths.
```javascript
{{call Class:Case1 $.valOfClass}} // Invoking a specific "case 1" for a Class

=== .../Class:Case1 ===
// Path block for case 1

=== .../Class:Case2 ===
// Path block for case 2
```

##### Referencing
- When inside a path block, you can access the current context [value](#value) simply by its path name `{{Class}}` or it alias name `{{Case1}}`.
- The following example show how you can reference the calling block [values](#value).
```javascript
=== ...CallingClass:ANCESTOR/MyClass:CURRENT ===

{{CallingClass.name}}.{{MyClass.name}} // Output both context names using their path/class names
{{ANCESTOR.name}}.{{CURRENT.name}} // Output both context names using their path aliases
```

#### <a id="command"></a>Call command
- When using a call command, it is possible to specify where you want to insert the text.
- Call and [values](#value) are pretty similar except that call commands are defined with **single braces** `{call PATH VALUE}` just like controls.
- You can use conditional to decide when to invoke a command `{if !$.test call PATH $.val}`.
- It is not permitted to call the root context `{call $ $.val}` since this would trigger an infinite loop!
```javascript
=== PATH ===
T0 // This is the normal text for this block
{call PATH_X $.val:T1} // PATH1 text is appended after this block (by default)
{call PATH_X $.val:T2 after} // Text is appended after the previous call
{call PATH_X $.val:T3 after PATH} // Text is appended after the previous call

// T0
// T1
// T2
// T3
```
- By default the `{call Class $.val}` command will be inserted after the current block. If the call command is invoked many times, then each invocation is *consecutive* to the preceding one.
- It is possible to insert the text *before* the current block. If the command is invoked many time, then each invocation is added *before* the previous one. It's just like the text has been glued before the current block and is now part of it.
```javascript
=== PATH ===
T0 // This is the normal text for this block

{call PATH_X $.val:T1 before} // Text is appended before this block
{call PATH_X $.val:T2 before PATH} // Text is appended before the previous call

{call PATH_X $.val:T3} // Text is appended after the current block
{call PATH_X $.val:T4 after} // Text is appended after the previous call
{call PATH_X $.val:T5 after PATH} // Text is appended after the previous call

// T2
// T1
// T0
// T3
// T4
// T5
```
- It is possible to reverse the order by using the *right before* or *right after* commands. 
```javascript
=== PATH ===
T0 // This is the normal text for this block
{call PATH_X $.val:T1 right before} // Text is appended right before this block
{call PATH_X $.val:T2 right before PATH} // Text is also appended right before this block

// T1
// T2
// T0
```
- In order to add some text *before* or *after* the whole document, use the special `$` [path](#path).
```javascript
T0 // This is the normal text for this document
{call PATH $.val}
{call PATH_X $.val:T1 after} // Text is appended after the document 
{call PATH_X $.val:T2 before} // Text is appended after the document 

=== PATH ===
{call PATH_X $.val:T3 after $} // Text is appended after the document 
{call PATH_X $.val:T4 before $} // Text is appended right before this block

// T2
// T4
// T0
// T3
// T1
```
- Where and when the `{call}` command is placed is as important as the command itself when determining *the placement* of the final text.

#### <a id="comment"></a>Comment, Escape & Ignore
- A comment begins with `###`. A comment terminates at the end of the line or when another `###` is encountered.
```javascript
123### // Will output 123 without a new line
123###456### // Will output 123 WITH a new line (and this java comment!!!)
```
- The **escape** character `~` will make any immediately following character, including itself, to be treated as normal text. This is the only character that always need to be escaped.
- Whenever possible, the engine will try to escape the invalid char sequences for you.
```javascript
~{begin test~}### // Will output {begin test} (without this comment!)
```
- An empty line immediately before or after a [path block](#path) header **will be ignored**.
- When a line ends with a `{control}` or a `{call}` the immediately following new line **will be ignored**.
```javascript
{begin ALIAS} 
{{ALIAS}} // No new line added before the value!
```
- [Notepad++](https://notepad-plus-plus.org/) is a very handy companion when editing your template scripts. Simply double click on any [alias](#alias) to immediately visualize its structure.

### Future developments
- Have a nice mecanism for error message handling.
- Should it use loggers or writers to dump the errors?
- Have a trace to follow the order of calling to debug the command calls
- Controls & commands in error are printed as is for convenience so it is easy to find the error in the script.

Please enjoy! S.Nadeau :-)