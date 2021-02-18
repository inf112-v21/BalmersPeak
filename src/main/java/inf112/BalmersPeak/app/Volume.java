package inf112.balmerspeak.app;

public class Volume {
    private float value;

    public Volume() {
        this.value = 0;
    }

    public float getValue() {
        return this.value;
    }

    public void setValue(float newValue) {
        this.value = newValue;
    }

    public void decreaseVolume() {
        this.value -= 1;
    }

    public void increaseVolume() {
        this.value += 1;
    }

}
