# QuickStart #

* Type annotations
* Interfaces
* Classes

## gulp ##

	npm install -g gulp-cli

	npm install --save-dev typescript gulp gulp-typescript

## code ##

### code1： ###

	function greeter(person: string) {
	    return "Hello, " + person;
	}
	
	var user = "Jane User";
	
	document.body.innerHTML = greeter(user);

### code2： ###

	function greeter(person: string) {
	    return "Hello, " + person;
	}
	
	var user = "Jane User";
	
	document.body.innerHTML = greeter(user);

### code3: ###

	class Student {
	    fullName: string;
	    constructor(public firstName, public middleInitial, public lastName) {
	        this.fullName = firstName + " " + middleInitial + " " + lastName;
	    }
	}
	
	interface Person {
	    firstName: string;
	    lastName: string;
	}
	
	function greeter(person : Person) {
	    return "Hello, " + person.firstName + " " + person.lastName;
	}
	
	var user = new Student("Jane", "M.", "User");

	document.body.innerHTML = greeter(user);
