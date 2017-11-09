import processing.core.PApplet;

import javax.swing.*;

public class UselessMainClass extends PApplet {

    public static void main(String[] args) {
        PApplet.main("UselessMainClass");
    }

    Circulos circulos;
    Cuadrados cuadrados;
    Elipses elipses;
    boolean dibujar, colorFullMain;
    int sw;

    public void settings() {
        size(650, 650);
    }

    public void setup() {
        JOptionPane.showMessageDialog(null, "INSTRUCCIONES DE USO:\n" +
                "P - Pausar dibujado.\n" +
                "Q - Cambiar paleta de colores.\n" +
                "E - Exportar a png.\n" +
                "R - Reiniciar imagen.\n" +
                "Z - Dibujado con cuadrados.\n" +
                "X - Dibujado con circulos.\n" +
                "C - Dibujado con elipses.");
        colorFullMain = false;
        sw = 1;
        dibujar = true;
        frameRate(100);
        circulos = new Circulos(colorFullMain);
        cuadrados = new Cuadrados(colorFullMain);
        elipses = new Elipses(colorFullMain);
        background(200);
    }

    public void keyPressed() {
        switch (key) {
            case 'z':
            case 'Z':
                background(200);
                cuadrados = new Cuadrados(colorFullMain);
                sw = 1;
                break;
            case 'c':
            case 'C':
                background(200);
                elipses = new Elipses(colorFullMain);
                sw = 2;
                break;
            case 'x':
            case 'X':
                background(200);
                circulos = new Circulos(colorFullMain);
                sw = 3;
                break;
            case 'r':
            case 'R':
                background(200);
                circulos = new Circulos(colorFullMain);
                cuadrados = new Cuadrados(colorFullMain);
                elipses = new Elipses(colorFullMain);
                break;
            case 'p':
            case 'P':
                dibujar = !dibujar;
                break;
            case 'e':
            case 'E':
                saveFrame("export-#####.png");
                break;
            case 'q':
            case 'Q':
                background(200);
                colorFullMain = !colorFullMain;
                circulos = new Circulos(colorFullMain);
                cuadrados = new Cuadrados(colorFullMain);
                elipses = new Elipses(colorFullMain);
                break;
        }
    }

    public void draw() {
        if (dibujar) {
            switch (sw) {
                case 1:
                    pushMatrix();
                    cuadrados.Espiral();
                    popMatrix();
                    break;
                case 2:
                    pushMatrix();
                    elipses.Espiral();
                    popMatrix();
                    break;
                case 3:
                    pushMatrix();
                    circulos.Espiral();
                    popMatrix();
                    break;
            }
        }
    }

    private class Cuadrados {

        float x, y, ancho, r, g, b, rad;
        int aleatorio;
        boolean colorFull;

        public Cuadrados(boolean colorFull) {
            this.x = 0;
            this.y = 0;
            this.ancho = 55;
            this.rad = 1;
            this.aleatorio = 0;
            this.colorFull = colorFull;
            Colores();
        }

        public void Colores() {
            int color = (int) random(0, 9);
            if (this.aleatorio == 0) {
                PaletaBases(color);
            } else {
                if (colorFull) {
                    if (this.aleatorio % 2 == 1) {
                        if ((int) random(0, 2) == 0) {
                            PaletaVivos(color);
                        } else {
                            PaletaTierra(color);
                        }
                    } else {
                        PaletaBases(color);
                    }
                } else {
                    if (this.aleatorio % 2 == 1) {
                        if ((int) random(0, 2) == 0) {
                            PaletaBases(color);
                        } else {
                            PaletaTierra(color);
                        }
                    } else {
                        PaletaBases(color);
                    }
                }
            }
        }

        public void Espiral() {
            translate(width / 2, height / 2);
            rotate(this.rad);
            strokeWeight((float) 0.7);
            stroke(0, 0, 0);
            fill(this.r, this.g, this.b);
            rect(this.x, this.y, this.ancho, this.ancho);
            this.rad++;
            this.x += 0.5;
            this.y += 0.5;
            if (this.x > width - 350 && this.y > height - 350) {
                Resetear();
            }
        }

        public void Resetear() {
            this.x = 0;
            this.y = 0;
            this.rad = 1;
            this.aleatorio++;
            if (this.ancho > 0) {
                this.ancho -= 5;
            }
            Colores();
            if (this.ancho == 5) {
                this.r = 85;
                this.g = 85;
                this.b = 85;
            }
        }

        public void PaletaTierra(int aleatorio) {
            switch (aleatorio) {
                case 0:
                    this.r = 255;
                    this.g = 153;
                    this.b = 41;
                    break;
                case 1:
                    this.r = 73;
                    this.g = 83;
                    this.b = 118;
                    break;
                case 2:
                    this.r = 217;
                    this.g = 0;
                    this.b = 84;
                    break;
                case 3:
                    this.r = 124;
                    this.g = 169;
                    this.b = 255;
                    break;
                case 4:
                    this.r = 56;
                    this.g = 188;
                    this.b = 190;
                    break;
                case 5:
                    this.r = 255;
                    this.g = 99;
                    this.b = 75;
                    break;
                case 6:
                    this.r = 178;
                    this.g = 24;
                    this.b = 0;
                    break;
                case 7:
                    this.r = 109;
                    this.g = 120;
                    this.b = 81;
                    break;
                default:
                    this.r = 75;
                    this.g = 75;
                    this.b = 75;
            }
        }

        public void PaletaVivos(int aleatorio) {
            switch (aleatorio) {
                case 0:
                    this.r = 160;
                    this.g = 255;
                    this.b = 41;
                    break;
                case 1:
                    this.r = 255;
                    this.g = 41;
                    this.b = 94;
                    break;
                case 2:
                    this.r = 50;
                    this.g = 197;
                    this.b = 255;
                    break;
                case 3:
                    this.r = 41;
                    this.g = 255;
                    this.b = 189;
                    break;
                case 4:
                    this.r = 255;
                    this.g = 79;
                    this.b = 41;
                    break;
                case 5:
                    this.r = 255;
                    this.g = 27;
                    this.b = 115;
                    break;
                case 6:
                    this.r = 202;
                    this.g = 108;
                    this.b = 255;
                    break;
                case 7:
                    this.r = 41;
                    this.g = 255;
                    this.b = 189;
                    break;
                default:
                    this.r = 0;
                    this.g = 200;
                    this.b = 0;
            }
        }

        public void PaletaBases(int aleatorio) {
            switch (aleatorio) {
                case 0:
                    this.r = 250;
                    this.g = 170;
                    this.b = 255;
                    break;
                case 1:
                    this.r = 220;
                    this.g = 221;
                    this.b = 225;
                    break;
                case 2:
                    this.r = 255;
                    this.g = 228;
                    this.b = 110;
                    break;
                case 3:
                    this.r = 195;
                    this.g = 215;
                    this.b = 255;
                    break;
                case 4:
                    this.r = 170;
                    this.g = 229;
                    this.b = 230;
                    break;
                case 5:
                    this.r = 222;
                    this.g = 226;
                    this.b = 212;
                    break;
                case 6:
                    this.r = 246;
                    this.g = 255;
                    this.b = 130;
                    break;
                case 7:
                    this.r = 148;
                    this.g = 119;
                    this.b = 212;
                    break;
                default:
                    this.r = 205;
                    this.g = 205;
                    this.b = 205;
            }
        }


    }

    private class Circulos {

        float x, y, ancho, r, g, b, rad;
        int aleatorio;
        boolean colorFull;

        public Circulos(boolean colorFull) {
            this.x = 0;
            this.y = 0;
            this.ancho = 65;
            this.rad = 1;
            this.aleatorio = 0;
            this.colorFull = colorFull;
            Colores();
        }

        public void Colores() {
            int color = (int) random(0, 9);
            if (this.aleatorio == 0) {
                PaletaBases(color);
            } else {
                if (colorFull) {
                    if (this.aleatorio % 2 == 1) {
                        if ((int) random(0, 2) == 0) {
                            PaletaVivos(color);
                        } else {
                            PaletaTierra(color);
                        }
                    } else {
                        PaletaBases(color);
                    }
                } else {
                    if (this.aleatorio % 2 == 1) {
                        if ((int) random(0, 2) == 0) {
                            PaletaBases(color);
                        } else {
                            PaletaTierra(color);
                        }
                    } else {
                        PaletaBases(color);
                    }
                }
            }
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
            if (this.x > width - 300 && this.y > height - 300) {
                Resetear();
            }
        }

        public void Resetear() {
            this.x = 0;
            this.y = 0;
            this.rad = 1;
            this.aleatorio++;
            if (this.ancho > 0) {
                this.ancho -= 5;
            }
            Colores();
            if (this.ancho == 5) {
                this.r = 85;
                this.g = 85;
                this.b = 85;
            }
        }

        public void PaletaTierra(int aleatorio) {
            switch (aleatorio) {
                case 0:
                    this.r = 255;
                    this.g = 153;
                    this.b = 41;
                    break;
                case 1:
                    this.r = 73;
                    this.g = 83;
                    this.b = 118;
                    break;
                case 2:
                    this.r = 217;
                    this.g = 0;
                    this.b = 84;
                    break;
                case 3:
                    this.r = 124;
                    this.g = 169;
                    this.b = 255;
                    break;
                case 4:
                    this.r = 56;
                    this.g = 188;
                    this.b = 190;
                    break;
                case 5:
                    this.r = 255;
                    this.g = 99;
                    this.b = 75;
                    break;
                case 6:
                    this.r = 178;
                    this.g = 24;
                    this.b = 0;
                    break;
                case 7:
                    this.r = 109;
                    this.g = 120;
                    this.b = 81;
                    break;
                default:
                    this.r = 75;
                    this.g = 75;
                    this.b = 75;
            }
        }

        public void PaletaVivos(int aleatorio) {
            switch (aleatorio) {
                case 0:
                    this.r = 160;
                    this.g = 255;
                    this.b = 41;
                    break;
                case 1:
                    this.r = 255;
                    this.g = 41;
                    this.b = 94;
                    break;
                case 2:
                    this.r = 50;
                    this.g = 197;
                    this.b = 255;
                    break;
                case 3:
                    this.r = 41;
                    this.g = 255;
                    this.b = 189;
                    break;
                case 4:
                    this.r = 255;
                    this.g = 79;
                    this.b = 41;
                    break;
                case 5:
                    this.r = 255;
                    this.g = 27;
                    this.b = 115;
                    break;
                case 6:
                    this.r = 202;
                    this.g = 108;
                    this.b = 255;
                    break;
                case 7:
                    this.r = 41;
                    this.g = 255;
                    this.b = 189;
                    break;
                default:
                    this.r = 0;
                    this.g = 200;
                    this.b = 0;
            }
        }

        public void PaletaBases(int aleatorio) {
            switch (aleatorio) {
                case 0:
                    this.r = 250;
                    this.g = 170;
                    this.b = 255;
                    break;
                case 1:
                    this.r = 220;
                    this.g = 221;
                    this.b = 225;
                    break;
                case 2:
                    this.r = 255;
                    this.g = 228;
                    this.b = 110;
                    break;
                case 3:
                    this.r = 195;
                    this.g = 215;
                    this.b = 255;
                    break;
                case 4:
                    this.r = 170;
                    this.g = 229;
                    this.b = 230;
                    break;
                case 5:
                    this.r = 222;
                    this.g = 226;
                    this.b = 212;
                    break;
                case 6:
                    this.r = 246;
                    this.g = 255;
                    this.b = 130;
                    break;
                case 7:
                    this.r = 148;
                    this.g = 119;
                    this.b = 212;
                    break;
                default:
                    this.r = 205;
                    this.g = 205;
                    this.b = 205;
            }
        }

    }

    private class Elipses {

        float x, y, ancho, r, g, b, rad;
        int aleatorio;
        boolean p, colorFull;

        public Elipses(boolean colorFull) {
            this.x = 0;
            this.y = 0;
            this.ancho = 45;
            this.rad = 1;
            this.aleatorio = 0;
            this.p = true;
            this.colorFull = colorFull;
            Colores();
        }

        public void Colores() {
            int color = (int) random(0, 9);
            if (this.aleatorio == 0) {
                PaletaBases(color);
            } else {
                if (colorFull) {
                    if (this.aleatorio % 2 == 1) {
                        if ((int) random(0, 2) == 0) {
                            PaletaVivos(color);
                        } else {
                            PaletaTierra(color);
                        }
                    } else {
                        PaletaBases(color);
                    }
                } else {
                    if (this.aleatorio % 2 == 1) {
                        if ((int) random(0, 2) == 0) {
                            PaletaBases(color);
                        } else {
                            PaletaTierra(color);
                        }
                    } else {
                        PaletaBases(color);
                    }
                }
            }
        }

        public void Espiral() {
            translate(width / 2, height / 2);
            rotate(this.rad);
            strokeWeight((float) 0.7);
            stroke(0, 0, 0);
            fill(this.r, this.g, this.b);
            if (this.ancho >= 5) {
                if (this.p) {
                    ellipse(this.x, this.y, this.ancho, this.ancho * 2);
                } else {
                    ellipse(this.x, this.y, this.ancho * 2, this.ancho);
                }
            }
            this.rad++;
            this.x += 0.5;
            this.y += 0.5;
            if (this.x > width - 300 && this.y > height - 300) {
                Resetear();
            }
        }

        public void Resetear() {
            this.x = 0;
            this.y = 0;
            this.rad = 1;
            this.p = !this.p;
            this.aleatorio++;
            if (this.ancho > 0) {
                this.ancho -= 5;
            }
            Colores();
            if (this.ancho == 5) {
                this.r = 85;
                this.g = 85;
                this.b = 85;
            }
        }

        public void PaletaTierra(int aleatorio) {
            switch (aleatorio) {
                case 0:
                    this.r = 255;
                    this.g = 153;
                    this.b = 41;
                    break;
                case 1:
                    this.r = 73;
                    this.g = 83;
                    this.b = 118;
                    break;
                case 2:
                    this.r = 217;
                    this.g = 0;
                    this.b = 84;
                    break;
                case 3:
                    this.r = 124;
                    this.g = 169;
                    this.b = 255;
                    break;
                case 4:
                    this.r = 56;
                    this.g = 188;
                    this.b = 190;
                    break;
                case 5:
                    this.r = 255;
                    this.g = 99;
                    this.b = 75;
                    break;
                case 6:
                    this.r = 178;
                    this.g = 24;
                    this.b = 0;
                    break;
                case 7:
                    this.r = 109;
                    this.g = 120;
                    this.b = 81;
                    break;
                default:
                    this.r = 75;
                    this.g = 75;
                    this.b = 75;
            }
        }

        public void PaletaVivos(int aleatorio) {
            switch (aleatorio) {
                case 0:
                    this.r = 160;
                    this.g = 255;
                    this.b = 41;
                    break;
                case 1:
                    this.r = 255;
                    this.g = 41;
                    this.b = 94;
                    break;
                case 2:
                    this.r = 50;
                    this.g = 197;
                    this.b = 255;
                    break;
                case 3:
                    this.r = 41;
                    this.g = 255;
                    this.b = 189;
                    break;
                case 4:
                    this.r = 255;
                    this.g = 79;
                    this.b = 41;
                    break;
                case 5:
                    this.r = 255;
                    this.g = 27;
                    this.b = 115;
                    break;
                case 6:
                    this.r = 202;
                    this.g = 108;
                    this.b = 255;
                    break;
                case 7:
                    this.r = 41;
                    this.g = 255;
                    this.b = 189;
                    break;
                default:
                    this.r = 0;
                    this.g = 200;
                    this.b = 0;
            }
        }

        public void PaletaBases(int aleatorio) {
            switch (aleatorio) {
                case 0:
                    this.r = 250;
                    this.g = 170;
                    this.b = 255;
                    break;
                case 1:
                    this.r = 220;
                    this.g = 221;
                    this.b = 225;
                    break;
                case 2:
                    this.r = 255;
                    this.g = 228;
                    this.b = 110;
                    break;
                case 3:
                    this.r = 195;
                    this.g = 215;
                    this.b = 255;
                    break;
                case 4:
                    this.r = 170;
                    this.g = 229;
                    this.b = 230;
                    break;
                case 5:
                    this.r = 222;
                    this.g = 226;
                    this.b = 212;
                    break;
                case 6:
                    this.r = 246;
                    this.g = 255;
                    this.b = 130;
                    break;
                case 7:
                    this.r = 148;
                    this.g = 119;
                    this.b = 212;
                    break;
                default:
                    this.r = 205;
                    this.g = 205;
                    this.b = 205;
            }
        }


    }

}
