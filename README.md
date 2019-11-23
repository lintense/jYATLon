# Note: This project has just been started and it merely compiles... 
---

# j-YATL-on
![Yet Another Template Language](https://github.com/lintense/jYATLon/blob/master/img/logo.png)
## *Yet Another Template Language...*

---
### Why YATL?
Most current template language are procedural, so they look like code, not like template... So they are (at times) hard to understand and need to be debugged, just like code!
So what about a new *declarative* templating language build from the groud up to look just like a *real* template language?
Doing so would provide the following benefits:

- **Clarity:** No `${#if#elseif#endif}` gibberish.
- **Java 8 compatibility:** Because some of us are not yet cutting edge!
- **Simplicity:** Easy to understand, easy to adapt!
- **Performance**... when possible!
- **No dependency**

### <a id="running"></a>Running the program
- Create a template file containing the following text: `Hello [$]!`
- Execute the following code to create a context and invoke the engine:
```java
YATL yatl = YATL.getTemplate("filename");
Object root = "World"; // This is the root context
Writer writer = new StringWriter();
yatl.merge(root, writer);
```
### Tutorial
- YATL is intended to be the simplest templating language possible. This is why it does only one thing, hopefully well!
- As a general rule, all [values](#value) and [commands](#command) **MUST** begin and end on the same line.
- Also, be aware that spaces are not allowed inside [value](#value) expressions, [commands](#command) and [paths](#path).

#### <a id="root"></a>Root context
- The root context aka `$` refers to the argument that is passed when launching the template engine. (See [running the program](#running) above)
- It is recommended that you have full control over the root object so you can implement any special formating services that you may need.

#### <a id="value"></a>Value
- A value is the result of an expression enclosed in brackets `[]`.
- It can be a constant: `['any text']` or `["I'm a text!"]`
- The result of the expression will be written at that position.
- The value expression always starts with any of: the [root](#root), a [path](#path) or an [alias](#alias). Then, you may apply any functions that exist for this object and so on. For exemple you could do `[$.toString]` or `[$.toString.substring(1,4)]`.
- You can invoke the accessors with `[$.getSize()]` or `[$.getSize]` or even `[$.size]` if you prefer.
- If the result of a value expression can be empty or is any kind of collection, then the value **MUST** have an [alias](#alias) and **MUST** be enclosed in a [block](#block) .
- A value expression **MUST NOT** contain spaces.
- Alternative notation for value, in case you need it `{[ 'same value' ]}`.

##### Conditional output
- A value can be output conditionnaly by including a test `[if $.test 'This is good!']`. 
- To inverse the result of the test, use `!` as in `[if !$.test 'Oops! Sorry...']`.
- You can check if 2 values are equal `[if $.v1 == $v2 $.v1:ALIAS]` or not equal `[if $.v1 != $v2 $.v2:ALIAS]`.
- Since a *none activated* value will be empty, it **MUST** be given an [alias](#alias) and **MUST** be enclosed in a [block](#block). Constant values do not require a [block](#block).

##### Collection
- In case the [value](#value) is a `Collection` it can be usefull to know about the *index of* one element or the *size of* the `Collection` itself.
- The `indexOf` function returns the index of the current [value](#value) starting by `1`: `[indexOf($.val)]`.
 - The `sizeOf` function returns the number of [values](#value) that are visible for the [alias](#alias): `[sizeOf($.val:ALIAS)]`.
- `Collection` functions may be used inside a condition: `[if indexOf($.val) == 1 'First:']`.
- Conditional outputs have **no impact** on the final size of the `Collection`. [Values](#value) that are not activated are considered to be [optional aliases](#matching)


But when computing the size, all the conditional [values](#value) are considered true. This is because
`sizeOf` a `Collection`, all the elements are taken into account, not only the 


#### <a id="alias"></a>Alias
- You may assign an alias to any part of the expression of a [value](#value). For exemple `[$:ROOT.toString:ROOT_AGAIN.substring(1,4):PART_OF_ROOT]`.
- Aliases **MUST** start with a letter or an underscore but they can also contain numbers: `[_A-Za-z][_A-Za-z0-9]*`.
- When you expect any part of a [value](#value) expression to be `null` or empty `""` or to return a `Collection` then you **MUST** provide an alias for it. This is because you will need to put these [values](#value) into a [block](#block). 

#### <a id="block"></a>Block
- A Block is delimited by controls and is always associated to a single [alias](#alias).
- Its role is to control the visibilty of the text that surrounds the [value](#value) associated with its [alias](#alias).
- A block always begins by `{begin ALIAS}` and always ends by `{end ALIAS}`.
```javascript
{begin ALIAS} // The most simple block!
    [$.val:ALIAS]
{end ALIAS}
```

##### Empty value
- You may include a `{empty ALIAS}` control followed by the text you want to appear when the [value](#value) is empty.
```javascript
{begin ALIAS}
x = [ALIAS] // Value Expression: always under {begin}
{empty ALIAS}
x = null; // Text to appear if the Value is empty
{end ALIAS}
```
##### Collection value
- When the associated [value](#value) is a `Collection`, you can also use the following controls **in this order**: `{before ALIAS}`, `{between ALIAS}`, `{after ALIAS}`, `{empty ALIAS}`.
- The expression containing the [alias](#alias) **MUST** be declared between de `{begin ALIAS}` and the following control.
```javascript
{begin ALIAS}
x = List( // Text to appear before the whole value. Assumed to be a Collection here...
[ALIAS] // Value Expression: always under {begin} 
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
- The previous example would return `x = List("a","b");` or `x = null;` in case there is nothing to display.

##### Short notation
- When the `before, between, after` sequences are short, you may use an alternative such as `{begin ALIAS |"|",|");}` for clarity. 
- The first character found after the [alias](#alias) **MUST** be repeated exactly 3 times in order to be correctly interpreted as the separator. So you can use any single character that you want for that nmatter.
- Remember that the whole control block **MUST** be declared on a single line.

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
- It is possible to have optional aliases `[$.value:?OPTIONAL_ALIAS:ALTERNATE_ALIAS]` that can be `null` when the others are not. Optional aliases **MUST** have an alternate alias since they can be empty.
```javascript
{begin ALIAS}
    [$.val:ALIAS]
    [ALIAS]
    [$.name:ALIAS]
{end ALIAS}
```
- The previous example will write 3 times the same [value](#value) or nothing if the [values](#value) are not matching.

#### <a id="path"></a>Path Block
- Basically, a path block is a block that is defined outside of where it is actually inserted.
##### Defining
- To define a path, use the following `=== PATH ===` syntax where *PATH* is the class of the context of that block.
- The calling context path may be added to the sequence `=== PATH1/PATH2 ===` so it can be referenced in the path block.
- Both path defined previously are absolute and as such can only be called by the [root](#root) path.
- A path can be defined so it can be called from any other path block `=== .../PATH ===`
- The path name **MUST NOT** contain spaces.

##### Calling 
- To insert a path block, use the `call` operator inside a [value](#value) `[call PATH $.val]`. Remember the path **MUST** match the actual class or interface returned by the [value](#value) expression.
- A path block can also be conditionnaly inserted `[if $.test call PATH $.val]`.
- You may also use [alias](#alias) to discriminate among multiple similar paths.
```javascript
[call Class1:Case1 $.getClass1] // Invoking a specific "case 1" path block

=== .../Class1:Case1 ===
// block for case 1

=== .../Class1:Case2 ===
// block for case 2
```

##### Referencing
- When inside a path block, you can access the current context [value](#value) simply by its path name `[PATH]`.
- The following example show how you can reference the calling block [values](#value).
```javascript
=== ...CallingClass:ANCESTOR/MyClass:CURRENT ===

[CallingClass.name].[MyClass.name] // Output both context names using their path/class names
[ANCESTOR.name].[CURRENT.name] // Output both context names using their path aliases
```

#### <a id="command"></a>Path Command
- When using a command, it is possible to specify *where* you want the text to be output.
- Commands and [values](#value) are pretty similar except that commands are defined with braces `{}`.
- By default the `{call PATH $.val}` command is called after the current block. If the command is invoked many time, then each invocation is added *after* the previous one.
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
- It is also possible to insert text *before* the current block. If the command is invoked many time, then each invocation is added *before* the previous one. It's just like the text has been glued before the current block and is now part of it.
```javascript
=== PATH ===
T0 // This is the normal text for this block
{call PATH_X $.val:T1 before} // Text is appended before this block
{call PATH_X $.val:T2 before PATH} // Text is appended before the previous call

// T2
// T1
// T0
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
- Where the `{call}` is placed and when is as important as the command itself when determining *the placament* of the final text.

#### <a id="comment"></a>Comment, Escape & Ignore
- Comments begin and end with `###`. 
```javascript
123### // Will output 123 without a new line
123###456### // Will output 123 WITH a new line (and this java comment!!!)
```
- The **escape** character `~` will make any single following character, including itself, to be treated as normal text.
- Whenever possible, the engine will try to escape the invalid char sequences for you.
```javascript
~{begin test~}### // Will output {begin test} (without this comment!)
```
- Any empty line before and after a [path](#path) header **will be ignored**.
- When a line ends with a `{control}` the following new line **will be ignored**.
```javascript
{begin ALIAS} 
[ALIAS] // No new line added before the value!
```
- Notepad++ is a very handy companion when editing your template scripts. Simply double click on any [alias](#alias) to immediately visualize their structure.

### Future developments
- Have a nice mecanism for error message handling ERROR_P1_P2
- Use a writer when compiling instead of loggers.
- Detect ~ cannot be used inside COMMANDS and VALUES (except {begin ‘...’})
- Detect any { before the enclosing }
- When parsing {, could check for valid COMMAND names to avoid using ~
- Have a trace to follow the order of calling to debug the command calls
- Controls & commands in error are printed as is for convenience so it is easy to find the error in the script.
- Regex to validade alias names. [A-Za-z_0-9]*


