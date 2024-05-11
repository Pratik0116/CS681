package edu.umb.cs681.hw12;

import java.util.concurrent.atomic.AtomicReference;

public class Aircraft {

    private final AtomicReference<Position> position;

    public Aircraft(Position pos) {
        this.position = new AtomicReference<>(pos);
    }

    public void setPosition(double newLat, double newLong, double newAlt) {
        Position currentPos = this.position.get();
        Position newPos = currentPos.change(newLat, newLong, newAlt);
        this.position.set(newPos);
    }

    public Position getPosition() {
        return this.position.get();
    }
}
