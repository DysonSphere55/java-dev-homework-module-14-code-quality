package goit.hw.ui;

public class TicTacToeUIImpl implements TicTacToeUI {
    private final char[] box;
    private boolean emptyBoxCondition;
    public TicTacToeUIImpl() {
        box = new char[] {
                '1', '2', '3', '4', '5', '6', '7', '8', '9'
        };
        emptyBoxCondition = false;
    }

    @Override
    public char[] getBox() {
        return box;
    }

    @Override
    public void printInitialBox() {
        String box_line_format = " %s | %s | %s \n";
        System.out.println("\n\n");
        System.out.printf(box_line_format, box[0], box[1], box[2]);
        System.out.println("-----------");
        System.out.printf(box_line_format, box[3], box[4], box[5]);
        System.out.println("-----------");
        System.out.printf(box_line_format, box[6], box[7], box[8]);
        System.out.println("\n");

        if(!emptyBoxCondition){
            byte index;
            for(index = 0; index < 9; index++)
                box[index] = ' ';
            emptyBoxCondition = true;
        }
    }
}
