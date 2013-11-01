package processamentoimagem;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class ProcessamentoImagem {

    protected static ArrayList<int[][]> matrizes = new ArrayList<int[][]>();
    protected static ArrayList<String> imageName = new ArrayList<String>();
    protected static IFuncao func;

    public ProcessamentoImagem() {
    }
    
    

    public static void main(String[] args){
                    
        boolean processar = true;
        if (args.length>=2){
            readImage(args[0]);
            switch(args[1].charAt(1)){
                case 'n':   func = new Negativo();
                            break;
                case 'p':   func = new Potencia(1.0, Double.parseDouble(args[2]));
                            break;
                case 's':   readImage(args[2]);
                            if(args[1].charAt(2)=='o'){
                                func = new Soma();
                            }else{
                                func = new Subtracao();
                            }
                            break;
                case 'f':   switch (args[2].charAt(1)){//caso filtro, existe outro parametro para qual
                                case 'l':   func = new FiltroLaplaciano();
                                            break;
                                case 's':   func = new FiltroSuavizacao(Integer.parseInt(args[3]));
                                            break;
                                case 'c':   func = new FiltroCustomizado();
                                            break;
                                case 'm':   switch (args[2].charAt(2)){
                                                case 'a':   func = new FiltroMax(Integer.parseInt(args[3]));
                                                            break;
                                                case 'i':   func = new FiltroMin(Integer.parseInt(args[3]));
                                                            break;
                                                case 'e':   func = new FiltroMediana(Integer.parseInt(args[3]));
                                                            break;
                                                case 'o':   func = new FiltroModa(Integer.parseInt(args[3]));
                                                            break;
                                                default:    processar = false;
                                                            System.out.println("Erro! Operação inválida!");
                                                            break;
                                            }
                                            break;
                                default:    processar = false;
                                            System.out.println("Erro! Operação inválida!");
                                            break;
                            }
                            break;
                case 'l':   func = new Limiar(Integer.parseInt(args[2]));
                            break;
                case 'e':   func = new Histograma();
                            break;
       /*         case 'h':   Histograma hist = new Histograma(matrizes);
                            hist.equalizar();
                            processar = false;
                            break;
        */        default:    processar = false;
                            System.out.println("Erro! Operação inválida!");
                            break;
            }
            if (processar){
                func.processarImagem(matrizes);
                func.printResultado(imageName);
            }
        }else{
            System.out.println("Erro! Ao menos um arquivo de imagem e uma operação devem ser passados como parâmetro!");
        }  
    }

    public static void readImage(String _imageName) {
        //reads a image, and fill out the working matriz, and prints a grayscale copy of the image---------
        BufferedImage imagem, imagemPB;
        imageName.add(_imageName);
        File foto = new File(imageName.get(imageName.size()-1));

        try {
            imagem = ImageIO.read(foto);

            int h = imagem.getHeight();
            int w = imagem.getWidth();
            int rgb, alpha, red, green, blue;
            int gray;

            int[][] matriz = new int[h][w];

            imagemPB = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);

            for (int i = 0; i < h; i++) {
                //System.out.print("\n");
                for (int j = 0; j < w; j++) {
                    //reads a pixel from the input image, as INT_ARGB
                    rgb = imagem.getRGB(i, j);

                    //decompose the value from the format INT_ARGB
                    alpha = (rgb >> 24) & 0xFF;
                    red = (rgb >> 16) & 0xFF;
                    green = (rgb >> 8) & 0xFF;
                    blue = (rgb) & 0xFF;
                    //System.out.print("\t"+alpha+","+red+","+green+","+blue);

                    //converts to 1 byte grayscale, and saves to the working matriz
                    gray = (int) (Math.round(red * 0.3 + green * 0.59 + blue * 0.11));
                    matriz[i][j] = gray;

                    //converts to 3 bytes grayscale
                    gray = gray + (gray << 8) + (gray << 16);

                    //System.out.print("\t"+gray);
                    imagemPB.setRGB(i, j, gray);

                    //System.out.print("\t"+gray);
                }
            }
            ImageIO.write(imagemPB, "PNG", new File(imageName.get(0) + "_GS.png"));
            matrizes.add(matriz);
        } catch (IOException e) {
        }
    }
}
