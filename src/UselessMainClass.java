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
        frameRate(200);
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
            this.ancho = 70;
            this.rad = 1;
            Colores();
        }

        public void Colores() {
            int color = (int) random(0, 20);
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
                case 4:
                    r = 255;
                    g = 153;
                    b = 41;
                    break;
                case 5:
                    r = 41;
                    g = 255;
                    b = 189;
                    break;
                case 6:
                    r = 255;
                    g = 79;
                    b = 41;
                    break;
                case 7:
                    r = 250;
                    g = 170;
                    b = 255;
                    break;
                case 8:
                    r = 73;
                    g = 83;
                    b = 118;
                    break;
                case 9:
                    r = 220;
                    g = 221;
                    b = 225;
                    break;
                case 10:
                    r = 255;
                    g = 27;
                    b = 115;
                    break;
                case 11:
                    r = 217;
                    g = 0;
                    b = 84;
                    break;
                case 12:
                    r = 202;
                    g = 108;
                    b = 255;
                    break;
                case 13:
                    r = 255;
                    g = 228;
                    b = 110;
                    break;
                case 14:
                    r = 124;
                    g = 169;
                    b = 255;
                    break;
                case 15:
                    r = 195;
                    g = 215;
                    b = 255;
                    break;
                case 16:
                    r = 170;
                    g = 229;
                    b = 230;
                    break;
                case 17:
                    r = 56;
                    g = 188;
                    b = 190;
                    break;
                case 18:
                    r = 255;
                    g = 99;
                    b = 75;
                    break;
                case 19:
                    r = 178;
                    g = 24;
                    b = 0;
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
