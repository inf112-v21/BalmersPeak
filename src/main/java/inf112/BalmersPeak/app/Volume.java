package inf112.balmerspeak.app;

public class Volume {
    private float value;

    public Volume() {
        this.value = 0.5f;
    }

    public float getValue() {
        return this.value;
    }

    public void setValue(float newValue) {
        this.value = newValue;
    }

    public void decreaseVolume() {
        if (this.value != 0.0) {
            this.value -= 0.1;
        }
    }

    public void increaseVolume() {
        if (this.value != 1.0) {
            this.value += 0.1;
        }
    }

}
