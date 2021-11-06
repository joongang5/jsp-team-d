function ajax_for_jsp(url, form, options) {
	
};

var Person = function(name) {
	this.name = name;
};

Person.prototype.getName = function() {
	return this.name;
}

var suzi = new Person("suzi");
suzi.__proto__.name = "suzi_proto";
console.log(suzi.__proto__.getName());

