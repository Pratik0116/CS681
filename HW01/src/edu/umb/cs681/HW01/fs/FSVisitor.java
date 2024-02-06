package edu.umb.cs681.HW01.fs;

public interface FSVisitor
{
	void visit(Link link);
	void visit(Directory directory);
	void visit(File file);
}
