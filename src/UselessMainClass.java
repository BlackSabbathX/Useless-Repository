import processing.core.PApplet;

public class UselessMainClass extends PApplet {

    public static void main(String[] args) {
        PApplet.main("UselessMainClass");
    }

    Circulos circulos;
    Cuadrados cuadrados;
    Elipses elipses;
    Lineas uno, dos, tres, cuatro;
    Lineas u, d, t, c;
    Particulas particulas;
    boolean dibujar, colorFullMain, inv;
    int sw, aux;
    float rgb, tx, ty, negativo;
    String str;

    public void settings() {
        size(650, 650);
    }

    public void setup() {
        aux = 0;
        textAlign(CENTER);
        textSize(15);
        tx = width / 2;
        ty = height / 2 - 140;
        str = "INSTRUCCIONES DE USO\n\n" +
                "P - Pausar dibujado.\n" +
                "Q - Cambiar paleta de colores.\n" +
                "E - Exportar a png.\n" +
                "R - Reiniciar imagen.\n\n" +
                "Z - Dibujado con cuadrados.\n" +
                "X - Dibujado con circulos.\n" +
                "C - Dibujado con elipses.\n\n" +
                "M - Regresar al menu.";
        colorFullMain = false;
        sw = 4;
        inv = true;
        rgb = 250;
        dibujar = true;
        frameRate(150);
        circulos = new Circulos(colorFullMain);
        cuadrados = new Cuadrados(colorFullMain);
        elipses = new Elipses(colorFullMain);
        particulas = new Particulas();
        uno = new Lineas(1);
        dos = new Lineas(2);
        tres = new Lineas(3);
        cuatro = new Lineas(4);
        u = new Lineas(1);
        d = new Lineas(2);
        t = new Lineas(3);
        c = new Lineas(4);
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
            case 'm':
            case 'M':
                circulos = new Circulos(colorFullMain);
                cuadrados = new Cuadrados(colorFullMain);
                elipses = new Elipses(colorFullMain);
                sw = 4;
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
                case 4:
                    background(rgb);
                    negativo = abs(rgb - 250);
                    uno.Dibujar(negativo);
                    dos.Dibujar(negativo);
                    tres.Dibujar(negativo);
                    cuatro.Dibujar(negativo);
                    fill(negativo);
                    if (inv) {
                        rgb--;
                    } else {
                        rgb++;
                    }
                    if (rgb == 0 || rgb == 250) {
                        inv = !inv;
                    }
                    text(str, tx, ty);
                    particulas.Dibujar(negativo);
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
            int color;
            if (this.aleatorio == 0) {
                color = (int) random(0, 30);
                PaletaBases(color);
            } else {
                if (colorFull) {
                    if (this.aleatorio % 2 == 1) {
                        if ((int) random(0, 4) == 0) {
                            color = (int) random(0, 22);
                            PaletaTierra(color);
                        } else {
                            color = (int) random(0, 37);
                            PaletaVivos(color);
                        }
                    } else {
                        color = (int) random(0, 30);
                        PaletaBases(color);
                    }
                } else {
                    if (this.aleatorio % 2 == 1) {
                        if ((int) random(0, 4) == 0) {
                            color = (int) random(0, 30);
                            PaletaBases(color);
                        } else {
                            color = (int) random(0, 22);
                            PaletaTierra(color);
                        }
                    } else {
                        color = (int) random(0, 30);
                        PaletaBases(color);
                    }
                }
            }
            System.out.println(color + "cuad");
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
            System.out.println("tierra");
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
                case 8:
                    this.r = 205;
                    this.g = 92;
                    this.b = 205;
                    break;
                case 9:
                    this.r = 220;
                    this.g = 20;
                    this.b = 60;
                    break;
                case 10:
                    this.r = 178;
                    this.g = 34;
                    this.b = 34;
                    break;
                case 11:
                    this.r = 139;
                    this.g = 0;
                    this.b = 0;
                    break;
                case 12:
                    this.r = 219;
                    this.g = 112;
                    this.b = 147;
                    break;
                case 13:
                    this.r = 255;
                    this.g = 215;
                    this.b = 0;
                    break;
                case 14:
                    this.r = 189;
                    this.g = 183;
                    this.b = 107;
                    break;
                case 15:
                    this.r = 240;
                    this.g = 230;
                    this.b = 140;
                    break;
                case 16:
                    this.r = 72;
                    this.g = 61;
                    this.b = 139;
                    break;
                case 17:
                    this.r = 222;
                    this.g = 184;
                    this.b = 135;
                    break;
                case 18:
                    this.r = 184;
                    this.g = 134;
                    this.b = 11;
                    break;
                case 19:
                    this.r = 139;
                    this.g = 69;
                    this.b = 19;
                    break;
                case 20:
                    this.r = 128;
                    this.g = 0;
                    this.b = 0;
                    break;
                case 21:
                    this.r = 160;
                    this.g = 82;
                    this.b = 45;
                    break;
                default:
                    this.r = 90;
                    this.g = 90;
                    this.b = 90;
            }
        }

        public void PaletaVivos(int aleatorio) {
            System.out.println("vivos");
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

                case 8:
                    this.r = 250;
                    this.g = 128;
                    this.b = 114;
                    break;
                case 9:
                    this.r = 255;
                    this.g = 0;
                    this.b = 0;
                    break;
                case 10:
                    this.r = 255;
                    this.g = 105;
                    this.b = 180;
                    break;
                case 11:
                    this.r = 255;
                    this.g = 20;
                    this.b = 147;
                    break;
                case 12:
                    this.r = 255;
                    this.g = 127;
                    this.b = 80;
                    break;
                case 13:
                    this.r = 255;
                    this.g = 99;
                    this.b = 71;
                    break;
                case 14:
                    this.r = 255;
                    this.g = 69;
                    this.b = 0;
                    break;
                case 15:
                    this.r = 255;
                    this.g = 140;
                    this.b = 0;
                    break;
                case 16:
                    this.r = 255;
                    this.g = 255;
                    this.b = 0;
                    break;
                case 17:
                    this.r = 255;
                    this.g = 0;
                    this.b = 255;
                    break;
                case 18:
                    this.r = 186;
                    this.g = 85;
                    this.b = 211;
                    break;
                case 19:
                    this.r = 147;
                    this.g = 112;
                    this.b = 219;
                    break;
                case 20:
                    this.r = 153;
                    this.g = 102;
                    this.b = 204;
                    break;
                case 21:
                    this.r = 148;
                    this.g = 0;
                    this.b = 211;
                    break;
                case 22:
                    this.r = 138;
                    this.g = 43;
                    this.b = 226;
                    break;
                case 23:
                    this.r = 75;
                    this.g = 0;
                    this.b = 130;
                    break;
                case 24:
                    this.r = 106;
                    this.g = 90;
                    this.b = 205;
                    break;
                case 25:
                    this.r = 173;
                    this.g = 255;
                    this.b = 47;
                    break;
                case 26:
                    this.r = 127;
                    this.g = 255;
                    this.b = 0;
                    break;
                case 27:
                    this.r = 0;
                    this.g = 255;
                    this.b = 0;
                    break;
                case 28:
                    this.r = 0;
                    this.g = 250;
                    this.b = 154;
                    break;
                case 29:
                    this.r = 0;
                    this.g = 250;
                    this.b = 127;
                    break;
                case 30:
                    this.r = 32;
                    this.g = 178;
                    this.b = 170;
                    break;
                case 31:
                    this.r = 0;
                    this.g = 255;
                    this.b = 255;
                    break;
                case 32:
                    this.r = 0;
                    this.g = 0;
                    this.b = 255;
                    break;
                case 33:
                    this.r = 0;
                    this.g = 0;
                    this.b = 205;
                    break;
                case 34:
                    this.r = 25;
                    this.g = 25;
                    this.b = 112;
                    break;
                case 35:
                    this.r = 224;
                    this.g = 164;
                    this.b = 96;
                    break;
                case 36:
                    this.r = 210;
                    this.g = 105;
                    this.b = 30;
                default:
                    this.r = 0;
                    this.g = 200;
                    this.b = 0;
            }
        }

        public void PaletaBases(int aleatorio) {
            System.out.println("bases");
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
                case 8:
                    this.r = 250;
                    this.g = 128;
                    this.b = 128;
                    break;
                case 9:
                    this.r = 255;
                    this.g = 160;
                    this.b = 122;
                    break;
                case 10:
                    this.r = 255;
                    this.g = 192;
                    this.b = 203;
                    break;
                case 11:
                    this.r = 255;
                    this.g = 160;
                    this.b = 122;
                    break;
                case 12:
                    this.r = 255;
                    this.g = 255;
                    this.b = 224;
                    break;
                case 13:
                    this.r = 255;
                    this.g = 250;
                    this.b = 205;
                    break;
                case 14:
                    this.r = 255;
                    this.g = 228;
                    this.b = 181;
                    break;
                case 15:
                    this.r = 255;
                    this.g = 218;
                    this.b = 185;
                    break;
                case 16:
                    this.r = 240;
                    this.g = 230;
                    this.b = 140;
                    break;
                case 17:
                    this.r = 230;
                    this.g = 230;
                    this.b = 250;
                    break;
                case 18:
                    this.r = 216;
                    this.g = 191;
                    this.b = 216;
                    break;
                case 19:
                    this.r = 221;
                    this.g = 160;
                    this.b = 221;
                    break;
                case 20:
                    this.r = 123;
                    this.g = 104;
                    this.b = 238;
                    break;
                case 21:
                    this.r = 152;
                    this.g = 251;
                    this.b = 152;
                    break;
                case 22:
                    this.r = 224;
                    this.g = 255;
                    this.b = 255;
                    break;
                case 23:
                    this.r = 176;
                    this.g = 224;
                    this.b = 230;
                    break;
                case 24:
                    this.r = 255;
                    this.g = 248;
                    this.b = 220;
                    break;
                case 25:
                    this.r = 255;
                    this.g = 235;
                    this.b = 205;
                    break;
                case 26:
                    this.r = 255;
                    this.g = 222;
                    this.b = 173;
                    break;
                case 27:
                    this.r = 240;
                    this.g = 255;
                    this.b = 240;
                    break;
                case 28:
                    this.r = 255;
                    this.g = 255;
                    this.b = 240;
                    break;
                case 29:
                    this.r = 255;
                    this.g = 228;
                    this.b = 225;
                default:
                    this.r = 250;
                    this.g = 250;
                    this.b = 250;
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
            int color;
            if (this.aleatorio == 0) {
                color = (int) random(0, 30);
                PaletaBases(color);
            } else {
                if (colorFull) {
                    if (this.aleatorio % 2 == 1) {
                        if ((int) random(0, 4) == 0) {
                            color = (int) random(0, 22);
                            PaletaTierra(color);
                        } else {
                            color = (int) random(0, 37);
                            PaletaVivos(color);
                        }
                    } else {
                        color = (int) random(0, 30);
                        PaletaBases(color);
                    }
                } else {
                    if (this.aleatorio % 2 == 1) {
                        if ((int) random(0, 4) == 0) {
                            color = (int) random(0, 30);
                            PaletaBases(color);
                        } else {
                            color = (int) random(0, 22);
                            PaletaTierra(color);
                        }
                    } else {
                        color = (int) random(0, 30);
                        PaletaBases(color);
                    }
                }
            }
            System.out.println(color + "circ");
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
            System.out.println("tierra");
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
                case 8:
                    this.r = 205;
                    this.g = 92;
                    this.b = 205;
                    break;
                case 9:
                    this.r = 220;
                    this.g = 20;
                    this.b = 60;
                    break;
                case 10:
                    this.r = 178;
                    this.g = 34;
                    this.b = 34;
                    break;
                case 11:
                    this.r = 139;
                    this.g = 0;
                    this.b = 0;
                    break;
                case 12:
                    this.r = 219;
                    this.g = 112;
                    this.b = 147;
                    break;
                case 13:
                    this.r = 255;
                    this.g = 215;
                    this.b = 0;
                    break;
                case 14:
                    this.r = 189;
                    this.g = 183;
                    this.b = 107;
                    break;
                case 15:
                    this.r = 240;
                    this.g = 230;
                    this.b = 140;
                    break;
                case 16:
                    this.r = 72;
                    this.g = 61;
                    this.b = 139;
                    break;
                case 17:
                    this.r = 222;
                    this.g = 184;
                    this.b = 135;
                    break;
                case 18:
                    this.r = 184;
                    this.g = 134;
                    this.b = 11;
                    break;
                case 19:
                    this.r = 139;
                    this.g = 69;
                    this.b = 19;
                    break;
                case 20:
                    this.r = 128;
                    this.g = 0;
                    this.b = 0;
                    break;
                case 21:
                    this.r = 160;
                    this.g = 82;
                    this.b = 45;
                    break;
                default:
                    this.r = 90;
                    this.g = 90;
                    this.b = 90;
            }
        }

        public void PaletaVivos(int aleatorio) {
            System.out.println("vivos");
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

                case 8:
                    this.r = 250;
                    this.g = 128;
                    this.b = 114;
                    break;
                case 9:
                    this.r = 255;
                    this.g = 0;
                    this.b = 0;
                    break;
                case 10:
                    this.r = 255;
                    this.g = 105;
                    this.b = 180;
                    break;
                case 11:
                    this.r = 255;
                    this.g = 20;
                    this.b = 147;
                    break;
                case 12:
                    this.r = 255;
                    this.g = 127;
                    this.b = 80;
                    break;
                case 13:
                    this.r = 255;
                    this.g = 99;
                    this.b = 71;
                    break;
                case 14:
                    this.r = 255;
                    this.g = 69;
                    this.b = 0;
                    break;
                case 15:
                    this.r = 255;
                    this.g = 140;
                    this.b = 0;
                    break;
                case 16:
                    this.r = 255;
                    this.g = 255;
                    this.b = 0;
                    break;
                case 17:
                    this.r = 255;
                    this.g = 0;
                    this.b = 255;
                    break;
                case 18:
                    this.r = 186;
                    this.g = 85;
                    this.b = 211;
                    break;
                case 19:
                    this.r = 147;
                    this.g = 112;
                    this.b = 219;
                    break;
                case 20:
                    this.r = 153;
                    this.g = 102;
                    this.b = 204;
                    break;
                case 21:
                    this.r = 148;
                    this.g = 0;
                    this.b = 211;
                    break;
                case 22:
                    this.r = 138;
                    this.g = 43;
                    this.b = 226;
                    break;
                case 23:
                    this.r = 75;
                    this.g = 0;
                    this.b = 130;
                    break;
                case 24:
                    this.r = 106;
                    this.g = 90;
                    this.b = 205;
                    break;
                case 25:
                    this.r = 173;
                    this.g = 255;
                    this.b = 47;
                    break;
                case 26:
                    this.r = 127;
                    this.g = 255;
                    this.b = 0;
                    break;
                case 27:
                    this.r = 0;
                    this.g = 255;
                    this.b = 0;
                    break;
                case 28:
                    this.r = 0;
                    this.g = 250;
                    this.b = 154;
                    break;
                case 29:
                    this.r = 0;
                    this.g = 250;
                    this.b = 127;
                    break;
                case 30:
                    this.r = 32;
                    this.g = 178;
                    this.b = 170;
                    break;
                case 31:
                    this.r = 0;
                    this.g = 255;
                    this.b = 255;
                    break;
                case 32:
                    this.r = 0;
                    this.g = 0;
                    this.b = 255;
                    break;
                case 33:
                    this.r = 0;
                    this.g = 0;
                    this.b = 205;
                    break;
                case 34:
                    this.r = 25;
                    this.g = 25;
                    this.b = 112;
                    break;
                case 35:
                    this.r = 224;
                    this.g = 164;
                    this.b = 96;
                    break;
                case 36:
                    this.r = 210;
                    this.g = 105;
                    this.b = 30;
                default:
                    this.r = 0;
                    this.g = 200;
                    this.b = 0;
            }
        }

        public void PaletaBases(int aleatorio) {
            System.out.println("bases");
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
                case 8:
                    this.r = 250;
                    this.g = 128;
                    this.b = 128;
                    break;
                case 9:
                    this.r = 255;
                    this.g = 160;
                    this.b = 122;
                    break;
                case 10:
                    this.r = 255;
                    this.g = 192;
                    this.b = 203;
                    break;
                case 11:
                    this.r = 255;
                    this.g = 160;
                    this.b = 122;
                    break;
                case 12:
                    this.r = 255;
                    this.g = 255;
                    this.b = 224;
                    break;
                case 13:
                    this.r = 255;
                    this.g = 250;
                    this.b = 205;
                    break;
                case 14:
                    this.r = 255;
                    this.g = 228;
                    this.b = 181;
                    break;
                case 15:
                    this.r = 255;
                    this.g = 218;
                    this.b = 185;
                    break;
                case 16:
                    this.r = 240;
                    this.g = 230;
                    this.b = 140;
                    break;
                case 17:
                    this.r = 230;
                    this.g = 230;
                    this.b = 250;
                    break;
                case 18:
                    this.r = 216;
                    this.g = 191;
                    this.b = 216;
                    break;
                case 19:
                    this.r = 221;
                    this.g = 160;
                    this.b = 221;
                    break;
                case 20:
                    this.r = 123;
                    this.g = 104;
                    this.b = 238;
                    break;
                case 21:
                    this.r = 152;
                    this.g = 251;
                    this.b = 152;
                    break;
                case 22:
                    this.r = 224;
                    this.g = 255;
                    this.b = 255;
                    break;
                case 23:
                    this.r = 176;
                    this.g = 224;
                    this.b = 230;
                    break;
                case 24:
                    this.r = 255;
                    this.g = 248;
                    this.b = 220;
                    break;
                case 25:
                    this.r = 255;
                    this.g = 235;
                    this.b = 205;
                    break;
                case 26:
                    this.r = 255;
                    this.g = 222;
                    this.b = 173;
                    break;
                case 27:
                    this.r = 240;
                    this.g = 255;
                    this.b = 240;
                    break;
                case 28:
                    this.r = 255;
                    this.g = 255;
                    this.b = 240;
                    break;
                case 29:
                    this.r = 255;
                    this.g = 228;
                    this.b = 225;
                default:
                    this.r = 250;
                    this.g = 250;
                    this.b = 250;
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
            int color;
            if (this.aleatorio == 0) {
                color = (int) random(0, 30);
                PaletaBases(color);
            } else {
                if (colorFull) {
                    if (this.aleatorio % 2 == 1) {
                        if ((int) random(0, 4) == 0) {
                            color = (int) random(0, 22);
                            PaletaTierra(color);
                        } else {
                            color = (int) random(0, 37);
                            PaletaVivos(color);
                        }
                    } else {
                        color = (int) random(0, 30);
                        PaletaBases(color);
                    }
                } else {
                    if (this.aleatorio % 2 == 1) {
                        if ((int) random(0, 4) == 0) {
                            color = (int) random(0, 30);
                            PaletaBases(color);
                        } else {
                            color = (int) random(0, 22);
                            PaletaTierra(color);
                        }
                    } else {
                        color = (int) random(0, 30);
                        PaletaBases(color);
                    }
                }
            }
            System.out.println(color + "elip");
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
            System.out.println("tierra");
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
                case 8:
                    this.r = 205;
                    this.g = 92;
                    this.b = 205;
                    break;
                case 9:
                    this.r = 220;
                    this.g = 20;
                    this.b = 60;
                    break;
                case 10:
                    this.r = 178;
                    this.g = 34;
                    this.b = 34;
                    break;
                case 11:
                    this.r = 139;
                    this.g = 0;
                    this.b = 0;
                    break;
                case 12:
                    this.r = 219;
                    this.g = 112;
                    this.b = 147;
                    break;
                case 13:
                    this.r = 255;
                    this.g = 215;
                    this.b = 0;
                    break;
                case 14:
                    this.r = 189;
                    this.g = 183;
                    this.b = 107;
                    break;
                case 15:
                    this.r = 240;
                    this.g = 230;
                    this.b = 140;
                    break;
                case 16:
                    this.r = 72;
                    this.g = 61;
                    this.b = 139;
                    break;
                case 17:
                    this.r = 222;
                    this.g = 184;
                    this.b = 135;
                    break;
                case 18:
                    this.r = 184;
                    this.g = 134;
                    this.b = 11;
                    break;
                case 19:
                    this.r = 139;
                    this.g = 69;
                    this.b = 19;
                    break;
                case 20:
                    this.r = 128;
                    this.g = 0;
                    this.b = 0;
                    break;
                case 21:
                    this.r = 160;
                    this.g = 82;
                    this.b = 45;
                    break;
                default:
                    this.r = 90;
                    this.g = 90;
                    this.b = 90;
            }
        }

        public void PaletaVivos(int aleatorio) {
            System.out.println("vivos");
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

                case 8:
                    this.r = 250;
                    this.g = 128;
                    this.b = 114;
                    break;
                case 9:
                    this.r = 255;
                    this.g = 0;
                    this.b = 0;
                    break;
                case 10:
                    this.r = 255;
                    this.g = 105;
                    this.b = 180;
                    break;
                case 11:
                    this.r = 255;
                    this.g = 20;
                    this.b = 147;
                    break;
                case 12:
                    this.r = 255;
                    this.g = 127;
                    this.b = 80;
                    break;
                case 13:
                    this.r = 255;
                    this.g = 99;
                    this.b = 71;
                    break;
                case 14:
                    this.r = 255;
                    this.g = 69;
                    this.b = 0;
                    break;
                case 15:
                    this.r = 255;
                    this.g = 140;
                    this.b = 0;
                    break;
                case 16:
                    this.r = 255;
                    this.g = 255;
                    this.b = 0;
                    break;
                case 17:
                    this.r = 255;
                    this.g = 0;
                    this.b = 255;
                    break;
                case 18:
                    this.r = 186;
                    this.g = 85;
                    this.b = 211;
                    break;
                case 19:
                    this.r = 147;
                    this.g = 112;
                    this.b = 219;
                    break;
                case 20:
                    this.r = 153;
                    this.g = 102;
                    this.b = 204;
                    break;
                case 21:
                    this.r = 148;
                    this.g = 0;
                    this.b = 211;
                    break;
                case 22:
                    this.r = 138;
                    this.g = 43;
                    this.b = 226;
                    break;
                case 23:
                    this.r = 75;
                    this.g = 0;
                    this.b = 130;
                    break;
                case 24:
                    this.r = 106;
                    this.g = 90;
                    this.b = 205;
                    break;
                case 25:
                    this.r = 173;
                    this.g = 255;
                    this.b = 47;
                    break;
                case 26:
                    this.r = 127;
                    this.g = 255;
                    this.b = 0;
                    break;
                case 27:
                    this.r = 0;
                    this.g = 255;
                    this.b = 0;
                    break;
                case 28:
                    this.r = 0;
                    this.g = 250;
                    this.b = 154;
                    break;
                case 29:
                    this.r = 0;
                    this.g = 250;
                    this.b = 127;
                    break;
                case 30:
                    this.r = 32;
                    this.g = 178;
                    this.b = 170;
                    break;
                case 31:
                    this.r = 0;
                    this.g = 255;
                    this.b = 255;
                    break;
                case 32:
                    this.r = 0;
                    this.g = 0;
                    this.b = 255;
                    break;
                case 33:
                    this.r = 0;
                    this.g = 0;
                    this.b = 205;
                    break;
                case 34:
                    this.r = 25;
                    this.g = 25;
                    this.b = 112;
                    break;
                case 35:
                    this.r = 224;
                    this.g = 164;
                    this.b = 96;
                    break;
                case 36:
                    this.r = 210;
                    this.g = 105;
                    this.b = 30;
                default:
                    this.r = 0;
                    this.g = 200;
                    this.b = 0;
            }
        }

        public void PaletaBases(int aleatorio) {
            System.out.println("bases");
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
                case 8:
                    this.r = 250;
                    this.g = 128;
                    this.b = 128;
                    break;
                case 9:
                    this.r = 255;
                    this.g = 160;
                    this.b = 122;
                    break;
                case 10:
                    this.r = 255;
                    this.g = 192;
                    this.b = 203;
                    break;
                case 11:
                    this.r = 255;
                    this.g = 160;
                    this.b = 122;
                    break;
                case 12:
                    this.r = 255;
                    this.g = 255;
                    this.b = 224;
                    break;
                case 13:
                    this.r = 255;
                    this.g = 250;
                    this.b = 205;
                    break;
                case 14:
                    this.r = 255;
                    this.g = 228;
                    this.b = 181;
                    break;
                case 15:
                    this.r = 255;
                    this.g = 218;
                    this.b = 185;
                    break;
                case 16:
                    this.r = 240;
                    this.g = 230;
                    this.b = 140;
                    break;
                case 17:
                    this.r = 230;
                    this.g = 230;
                    this.b = 250;
                    break;
                case 18:
                    this.r = 216;
                    this.g = 191;
                    this.b = 216;
                    break;
                case 19:
                    this.r = 221;
                    this.g = 160;
                    this.b = 221;
                    break;
                case 20:
                    this.r = 123;
                    this.g = 104;
                    this.b = 238;
                    break;
                case 21:
                    this.r = 152;
                    this.g = 251;
                    this.b = 152;
                    break;
                case 22:
                    this.r = 224;
                    this.g = 255;
                    this.b = 255;
                    break;
                case 23:
                    this.r = 176;
                    this.g = 224;
                    this.b = 230;
                    break;
                case 24:
                    this.r = 255;
                    this.g = 248;
                    this.b = 220;
                    break;
                case 25:
                    this.r = 255;
                    this.g = 235;
                    this.b = 205;
                    break;
                case 26:
                    this.r = 255;
                    this.g = 222;
                    this.b = 173;
                    break;
                case 27:
                    this.r = 240;
                    this.g = 255;
                    this.b = 240;
                    break;
                case 28:
                    this.r = 255;
                    this.g = 255;
                    this.b = 240;
                    break;
                case 29:
                    this.r = 255;
                    this.g = 228;
                    this.b = 225;
                default:
                    this.r = 250;
                    this.g = 250;
                    this.b = 250;
            }
        }

    }

    private class Lineas {

        float x1, y1, x2, y2;
        int esq;
        boolean inv;

        public Lineas(int esq) {
            this.esq = esq;
            this.inv = true;
            switch (this.esq) {
                case 1:
                    this.x1 = 0;
                    this.y1 = 0;
                    this.x2 = width;
                    this.y2 = 0;
                    break;
                case 2:
                    this.x1 = width;
                    this.y1 = 0;
                    this.x2 = width;
                    this.y2 = width;
                    break;
                case 3:
                    this.x1 = width;
                    this.y1 = width;
                    this.x2 = 0;
                    this.y2 = width;
                    break;
                case 4:
                    this.x1 = 0;
                    this.y1 = width;
                    this.x2 = 0;
                    this.y2 = 0;
                    break;
            }
        }

        public void Dibujar(float rgb) {
            stroke(rgb);
            strokeWeight(1);
            line(this.x1, this.y1, this.x2, this.y2);
            Avanzar();
        }

        private void Avanzar() {
            switch (this.esq) {
                case 1:
                    if (inv) {
                        this.x1++;
                        this.y2++;
                    } else {
                        this.x1--;
                        this.y2--;
                    }
                    if (this.x1 == width || (!this.inv && this.x1 == 0)) {
                        inv = !inv;
                    }
                    break;
                case 2:
                    if (inv) {
                        this.y1++;
                        this.x2--;
                    } else {
                        this.y1--;
                        this.x2++;
                    }
                    if (this.y1 == width || (!this.inv && this.y1 == 0)) {
                        inv = !inv;
                    }
                    break;
                case 3:
                    if (inv) {
                        this.x1--;
                        this.y2--;
                    } else {
                        this.x1++;
                        this.y2++;
                    }
                    if (this.x1 == 0 || (!this.inv && this.x1 == width)) {
                        inv = !inv;
                    }
                    break;
                case 4:
                    if (inv) {
                        this.y1--;
                        this.x2++;
                    } else {
                        this.y1++;
                        this.x2--;
                    }
                    if (this.y1 == 0 || (!this.inv && this.y1 == width)) {
                        inv = !inv;
                    }
                    break;
            }
        }
    }

    private class Particulas {

        float[] pivoteX, pivoteY, x, y, ancho;
        float max;
        int n;

        public Particulas() {
            this.n = 100;
            this.max = 1;
            this.x = new float[n];
            this.y = new float[n];
            this.pivoteX = new float[n];
            this.pivoteY = new float[n];
            this.ancho = new float[n];
            for (int i = 0; i < this.n; i++) {
                this.x[i] = random(0, width);
                this.y[i] = random(0, width);
                switch ((int) random(1, 5)) {
                    case 1:
                        this.pivoteX[i] = random(0, 1);
                        this.pivoteY[i] = random(0, 1);
                        break;
                    case 2:
                        this.pivoteX[i] = -random(0, 1);
                        this.pivoteY[i] = -random(0, 1);
                        break;
                    case 3:
                        this.pivoteX[i] = -random(0, 1);
                        this.pivoteY[i] = random(0, 1);
                        break;
                    case 4:
                        this.pivoteX[i] = random(0, 1);
                        this.pivoteY[i] = -random(0, 1);
                        break;
                }
                this.ancho[i] = random(1, 4);
            }
        }

        public void Dibujar(float rgb) {
            if (keyPressed) {
                if (this.max < 10) {
                    this.max += 0.5;
                }
            } else {
                if (this.max > 1) {
                    this.max -= 0.02;
                }
            }
            stroke(rgb);
            for (int i = 0; i < this.n; i++) {
                strokeWeight(this.ancho[i]);
                point(x[i], y[i]);
                if (this.pivoteX[i] == 1) {
                    x[i] += random(0, this.pivoteX[i]);
                    y[i] += random(0, this.pivoteY[i]);
                } else {
                    x[i] += random(this.pivoteX[i], 0);
                    y[i] += random(this.pivoteY[i], 0);
                }
                if (x[i] < 0) {
                    this.pivoteX[i] = random(this.max - 1, this.max);
                }
                if (y[i] < 0) {
                    this.pivoteY[i] = random(this.max - 1, this.max);
                }
                if (x[i] > width) {
                    this.pivoteX[i] = -random(this.max - 1, this.max);
                }
                if (y[i] > width) {
                    this.pivoteY[i] = -random(this.max - 1, this.max);
                }
            }
        }

    }

}
