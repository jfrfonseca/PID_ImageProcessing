package processamentoimagem;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ProcessamentoImagem {

    protected static int[][] matrix;

    public static void main(String[] args) throws IOException {
        readImage(args[0]);


    }

    private static void readImage(String imageName) {
        //reads a image, and fill out the working matrix, and prints a grayscale copy of the image---------
        BufferedImage imagem, imagemPB;

        File foto = new File(imageName);

        try {
            imagem = ImageIO.read(foto);

            int h = imagem.getHeight();
            int w = imagem.getWidth();
            int rgb, alpha, red, green, blue;
            int gray;

            matrix = new int[h][w];

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

                    //converts to 1 byte grayscale, and saves to the working matrix
                    gray = (int) (Math.round(red * 0.3 + green * 0.59 + blue * 0.11));
                    matrix[i][j] = gray;

                    //converts to 3 bytes grayscale
                    gray = gray + (gray << 8) + (gray << 16);

                    //System.out.print("\t"+gray);
                    imagemPB.setRGB(i, j, gray);

                    //System.out.print("\t"+gray);
                }
            }
            System.out.println("Done reading image '" + imageName + "'. Saving GrayScale output;");
            ImageIO.write(imagemPB, "PNG", new File(imageName + "_GS.png"));
        } catch (IOException e) {
        }
    }
}
