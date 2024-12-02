package config.web;

public enum Browser {
    FIREFOX, CHROME;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
