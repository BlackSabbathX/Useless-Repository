import processing.core.PApplet;

public class UselessMainClass extends PApplet {

    public static void main(String[] args) {
        PApplet.main("UselessMainClass");
    }

    Circulos circulos;

    public void settings() {
        size(650, 650);
    }

    public void setup() {
        frameRate(100);
        circulos = new Circulos(width / 2, height / 2);
        background(200, 200, 200);
    }

    public void draw() {
        circulos.Espiral();
    }

    private class Circulos {

        float x, y, ancho, r, g, b, rad;

        public Circulos(float x, float y) {
            this.x = 0;
            this.y = 0;
            this.ancho = 35;
            this.rad = 1;
            Colores();
        }

        public void Colores() {
            int color = (int) random(70, 250);
            float r, g, b;
            switch (color) {
                case 0:
                    r = 41;
                    g = 255;
                    b = 189;
                    break;
                case 1:
                    r = 160;
                    g = 255;
                    b = 41;
                    break;
                case 2:
                    r = 255;
                    g = 41;
                    b = 94;
                    break;
                case 3:
                    r = 50;
                    g = 197;
                    b = 255;
                    break;
                case 0:
                    r = 255;
                    g = 153;
                    b = 41;
                    break;
                case 0:
                    r = 41;
                    g = 255;
                    b = 189;
                    break;
                case 0:
                    r = 255;
                    g = 79;
                    b = 41;
                    break;
                case 0:
                    r = 250;
                    g = 170;
                    b = 255;
                    break;
                case 0:
                    r = 73
                    g = 83;
                    b = 118;
                    break;
                case 0:
                    r = 220;
                    g = 221;
                    b = 225;
                    break;
                case 0:
                    r = 255;
                    g = 27;
                    b = 115;
                    break;
                case 0:
                    r = 217;
                    g = 0;
                    b = 84;
                    break;
                case 0:
                    r = 41;
                    g = 255;
                    b = 189;
                    break;
                default:
                    r = 75;
                    g = 75;
                    b = 75;
            }
            this.r = r;
            this.g = g;
            this.b = b;
        }

        public void Espiral() {
            translate(width / 2, height / 2);
            rotate(this.rad);
            stroke(this.r, this.g, this.b);
            strokeWeight(this.ancho);
            point(this.x, this.y);
            this.rad++;
            this.x += 0.5;
            this.y += 0.5;
            if (this.x > width - 100 && this.y > height - 100) {
                Resetear();
            }
        }

        public void Resetear() {
            this.x = 0;
            this.y = 0;
            this.rad = 1;
            if (this.ancho > 0) {
                this.ancho -= 5;
            }
            Colores();
        }

    }


}
