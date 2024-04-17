package com.ncs.vehiclesimulation.model;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BaseVehicle {
    protected String id;
    protected Integer x;
    protected Integer y;
    protected Direction direction;
    protected String commands;
    public BaseVehicle(String id, Integer x, Integer y, Direction direction, String commands) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.commands = commands;
    }

    public BaseVehicle() {
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Integer getX() {
        return x;
    }
    public void setX(Integer x) {
        this.x = x;
    }
    public Integer getY() {
        return y;
    }
    public void setY(Integer y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getCommands() {
        return commands;
    }

    public void setCommands(String commands) {
        this.commands = commands;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseVehicle that = (BaseVehicle) o;
        return Objects.equals(id, that.id) && Objects.equals(x, that.x) && Objects.equals(y, that.y) && direction == that.direction && Objects.equals(commands, that.commands);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, x, y, direction, commands);
    }

    @Override
    public String toString() {
        return "BaseVehicle{" +
                "id='" + id + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", direction=" + direction +
                ", commands='" + commands + '\'' +
                '}';
    }
}
