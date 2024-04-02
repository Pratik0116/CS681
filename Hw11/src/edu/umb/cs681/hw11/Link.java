package edu.umb.cs681.hw11;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Link extends FSElement {
	private FSElement target;
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

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

	public FSElement getTarget() {
		lock.readLock().lock();
		try {
			return this.target;
		} finally {
			lock.readLock().unlock();
		}
	}

	public int getTargetSize() {
		lock.readLock().lock();
		try {
			return target.getSize();
		} finally {
			lock.readLock().unlock();
		}
	}

	public void setTarget(FSElement target) {
		lock.writeLock().lock();
		try {
			this.target = target;
		} finally {
			lock.writeLock().unlock();
		}
	}

	public boolean targetIsDirectory() {
		lock.readLock().lock();
		try {
			return target.isDirectory();
		} finally {
			lock.readLock().unlock();
		}
	}

	public boolean targetIsFile() {
		lock.readLock().lock();
		try {
			return target.isFile();
		} finally {
			lock.readLock().unlock();
		}
	}

	public boolean targetIsLink() {
		lock.readLock().lock();
		try {
			return target.isLink();
		} finally {
			lock.readLock().unlock();
		}
	}
}
