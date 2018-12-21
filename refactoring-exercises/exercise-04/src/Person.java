public class Person {
	public String last;

	public String first;

	public String middle;

		//a lot of classes with redunant functions

	public Person(String last, String first, String middle) {
		this.last = last;
		this.first = first;
		this.middle = middle;
	}


	@Override
	public String toString() {
		return first + " " + (middle == null ? "" : middle + " ") + last;
	}
}