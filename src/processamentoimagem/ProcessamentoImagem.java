package processamentoimagem;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class ProcessamentoImagem {

    protected static ArrayList<int[][]> matrizes = new ArrayList<int[][]>();
    protected static ArrayList<String> imageName = new ArrayList<String>();

    public static void main(String[] args) throws IOException {
        Negativo neg = new Negativo();
        Soma som = new Soma();
        Subtracao sub = new Subtracao();
        Potencia pot = new Potencia(Double.parseDouble(args[2]), Double.parseDouble(args[3]));
        
        
        readImage(args[0]);
        readImage(args[1]);
        
        neg.processarImagem(matrizes);
        neg.printResultado(imageName);
        
        som.processarImagem(matrizes);
        som.printResultado(imageName);
        
        sub.processarImagem(matrizes);
        sub.printResultado(imageName);
        
        pot.processarImagem(matrizes);
        pot.printResultado(imageName);

    }

    private static void readImage(String _imageName) {
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
