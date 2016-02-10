Don't delete the test.xml file please, it is used in the unit tests.


Patterns:

	Builder
		I used the builder pattern to clean up the interface to the Task object. 
I made the Task object kind of messy because it created an easy way of dealing with the import and export functions recursively. I added a list, and a boolean that might be deemed unnecessary, but these two variable prevented the creation of 3 other classes.

	
		I know that recursion isn't a pattern in itself, but I did a lot of it in this project to write much less code than otherwise needed.
	