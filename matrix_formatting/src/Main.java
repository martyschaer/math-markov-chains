import java.util.Arrays;

public class Main{
    public static void main(String[] args) {
        double[][] ms = {
                {-1, 0.5, 0.5, 0, -1, 1, 1, 0, -1},
        };

        for(double[] m : ms){
            printMatrixDef(m);
            System.out.println("-----------------------");
        }
    }

    private static void printMatrixDef(double[] m){
        if(m.length != 9){
            throw new IllegalArgumentException("should be 3x3 matrix, was " + Arrays.toString(m));
        }
        StringBuilder sb = new StringBuilder();
        sb.append("$$\n");
        sb.append("\\begin{bmatrix[rrr|]\n");
        int i = 1;
        for(double val : m){
            sb.append(String.format("%s & ", format(val)));
            if(i % 3 == 0){
                sb.append("0 &");
                if(i < m.length){
                    sb.append("\\\\\n");
                }else{
                    sb.append("\n");
                }
            }
            i++;
        }
        sb.append("\\end{bmatrix}\n");
        sb.append("$$\n");
        System.out.print(sb.toString());
    }

    private static String format(double val){
        if(val == (int)val){
            return String.format("%d", (int)val);
        }else{
            return String.format("%.1f", val);
        }
    }
}
/**
 $$
 \begin{bmatrix}[rrr|r]
 -1 & 0.5 & 0.5 & 0 \\
 0  & -1  & 1   & 0 \\
 1  & 0   & -1  & 0
 \end{bmatrix} = A - \lambda \cdot E \rightarrow A - 1 \cdot E
 $$
 */
