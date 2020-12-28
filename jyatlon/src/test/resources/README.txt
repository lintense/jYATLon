How this works:


After updating a file here:
In Eclipse, click on jyatlon project and select Maven > Update project...
To is required for Maven to move the files to the target repository

Test scripts must have extension: .script.txt (as defined by AppTest.TEST_SCRIPT_EXTENSION).
JUnit will automatically run all the script files located in the Resources folder.

To execute script manually (from AppTest.main()) change script folder to DEBUG.

To remove a script for execution, change script folder to NOT_TO_TEST.


