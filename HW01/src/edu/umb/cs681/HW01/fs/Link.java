package edu.umb.cs681.HW01.fs;

import java.time.LocalDateTime;

public class Link extends FSElement {

	private FSElement target;

	public Link(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target) {
		super(parent, name, size, creationTime);
		this.target = target;
	}

	public boolean isDirectory() {
		return false;
	}

	public boolean isFile() {
		return false;
	}

	public boolean isLink() {
		return true;
	}

	@Override
	public void accept(FSVisitor v) {
		v.visit(this);
	}

	public FSElement getTarget() {
		return this.target;
	}

	public int getTargetSize() {
		return target.getSize();
	}

	public void setTarget(FSElement target) {
		this.target = target;
	}

	public boolean targetisDirectory() {
		return target.isDirectory();
	}

	public boolean targetisFile() {
		return target.isFile();
	}

	public boolean targetisLink() {
		return target.isLink();
	}
}
