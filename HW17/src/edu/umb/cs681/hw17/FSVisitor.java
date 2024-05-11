package edu.umb.cs681.hw17;

public interface FSVisitor
{
	void visit(Link link);
	void visit(Directory directory);
	void visit(File file);
}
