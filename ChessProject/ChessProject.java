import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.JOptionPane;

/*
	This class can be used as a starting point for creating your Chess game project. The only piece that 
	has been coded is a white pawn...a lot done, more to do!
*/

public class ChessProject extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;
    int startX;
    int startY;
    int initialX;
    int initialY;
    JPanel panels;
    JLabel pieces;
    int turn = 0;

    public ChessProject() {
        Dimension boardSize = new Dimension(600, 600);

        //  Use a Layered Pane for this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane 
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout(new GridLayout(8, 8));
        chessBoard.setPreferredSize(boardSize);
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel(new BorderLayout());
            chessBoard.add(square);

            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground(i % 2 == 0 ? Color.white : Color.gray);
            else
                square.setBackground(i % 2 == 0 ? Color.gray : Color.white);
        }

        // Setting up the Initial Chess board.
        for (int i = 8; i < 16; i++) {
            pieces = new JLabel(new ImageIcon("WhitePawn.png"));
            panels = (JPanel) chessBoard.getComponent(i);
            panels.add(pieces);
        }
        pieces = new JLabel(new ImageIcon("WhiteRook.png"));
        panels = (JPanel) chessBoard.getComponent(0);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteKnight.png"));
        panels = (JPanel) chessBoard.getComponent(1);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteKnight.png"));
        panels = (JPanel) chessBoard.getComponent(6);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteBishop.png"));
        panels = (JPanel) chessBoard.getComponent(2);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteBishop.png"));
        panels = (JPanel) chessBoard.getComponent(5);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteKing.png"));
        panels = (JPanel) chessBoard.getComponent(3);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
        panels = (JPanel) chessBoard.getComponent(4);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteRook.png"));
        panels = (JPanel) chessBoard.getComponent(7);
        panels.add(pieces);
        for (int i = 48; i < 56; i++) {
            pieces = new JLabel(new ImageIcon("BlackPawn.png"));
            panels = (JPanel) chessBoard.getComponent(i);
            panels.add(pieces);
        }
        pieces = new JLabel(new ImageIcon("BlackRook.png"));
        panels = (JPanel) chessBoard.getComponent(56);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackKnight.png"));
        panels = (JPanel) chessBoard.getComponent(57);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackKnight.png"));
        panels = (JPanel) chessBoard.getComponent(62);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackBishop.png"));
        panels = (JPanel) chessBoard.getComponent(58);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackBishop.png"));
        panels = (JPanel) chessBoard.getComponent(61);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackKing.png"));
        panels = (JPanel) chessBoard.getComponent(59);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackQueen.png"));
        panels = (JPanel) chessBoard.getComponent(60);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackRook.png"));
        panels = (JPanel) chessBoard.getComponent(63);
        panels.add(pieces);
    }

    /*
        This method checks if there is a piece present on a particular square.
    */
    private Boolean piecePresent(int x, int y) {
        Component c = chessBoard.findComponentAt(x, y);
        if (c instanceof JPanel) {
            return false;
        } else {
            return true;
        }
    }

    private Boolean checkKingPresent(int newX, int newY) {

        Boolean kingPresent = false;

        Component c1 = chessBoard.findComponentAt(((newX + 75)), (newY)); //right
        Component c2 = chessBoard.findComponentAt((newX), ((newY + 75))); // below
        Component c3 = chessBoard.findComponentAt(((newX + 75)), ((newY + 75))); // bottom right
        Component c4 = chessBoard.findComponentAt(((newX - 75)), (newY)); // left
        Component c5 = chessBoard.findComponentAt((newX), ((newY - 75))); // above
        Component c6 = chessBoard.findComponentAt(((newX - 75)), ((newY - 75))); // top left
        Component c7 = chessBoard.findComponentAt(((newX + 75)), ((newY - 75))); // top right
        Component c8 = chessBoard.findComponentAt(((newX - 75)), ((newY + 75))); // bottom left

        if (c1 != null) {
            if (!(c1 instanceof JPanel)) {
                JLabel awaitingPiece1 = (JLabel) c1;
                String tmp1 = awaitingPiece1.getIcon().toString();
                if (tmp1.contains("King")) {
                    kingPresent = true;
                }
            }
        }

        if (c2 != null) {
            if (!(c2 instanceof JPanel)) {
                JLabel awaitingPiece2 = (JLabel) c2;
                String tmp2 = awaitingPiece2.getIcon().toString();
                if (tmp2.contains("King") && (tmp2 != null)) {
                    kingPresent = true;
                }
            }
        }

        if (c3 != null) {
            if (!(c3 instanceof JPanel)) {
                JLabel awaitingPiece3 = (JLabel) c3;
                String tmp3 = awaitingPiece3.getIcon().toString();
                if (tmp3.contains("King") && (tmp3 != null)) {
                    kingPresent = true;
                }
            }
        }

        if (c4 != null) {
            if (!(c4 instanceof JPanel)) {
                JLabel awaitingPiece4 = (JLabel) c4;
                String tmp4 = awaitingPiece4.getIcon().toString();
                if (tmp4.contains("King")) {
                    kingPresent = true;
                }
            }
        }

        if (c5 != null) {
            if (!(c5 instanceof JPanel)) {
                JLabel awaitingPiece5 = (JLabel) c5;
                String tmp5 = awaitingPiece5.getIcon().toString();
                if (tmp5.contains("King")) {
                    kingPresent = true;
                }
            }
        }

        if (c6 != null) {
            if (!(c6 instanceof JPanel)) {
                JLabel awaitingPiece6 = (JLabel) c6;
                String tmp6 = awaitingPiece6.getIcon().toString();
                if (tmp6.contains("King")) {
                    kingPresent = true;
                }
            }
        }

        if (c7 != null) {
            if (!(c7 instanceof JPanel)) {
                JLabel awaitingPiece7 = (JLabel) c7;
                String tmp7 = awaitingPiece7.getIcon().toString();
                if (tmp7.contains("King")) {
                    kingPresent = true;
                }
            }
        }

        if (c8 != null) {
            if (!(c8 instanceof JPanel)) {
                JLabel awaitingPiece8 = (JLabel) c8;
                String tmp8 = awaitingPiece8.getIcon().toString();
                if (tmp8.contains("King")) {
                    kingPresent = true;
                }
            }
        }

        if (kingPresent) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean checkWhiteKingTaken(int newX, int newY) {
        Component c1 = chessBoard.findComponentAt(newX, newY);
        JLabel awaitingPiece = (JLabel) c1;
        String tmp1 = awaitingPiece.getIcon().toString();
        if (((tmp1.contains("King")))) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean checkBlackKingTaken(int newX, int newY) {
        Component c1 = chessBoard.findComponentAt(newX, newY);
        JLabel awaitingPiece = (JLabel) c1;
        String tmp1 = awaitingPiece.getIcon().toString();
        if (((tmp1.contains("King")))) {
            return true;
        } else {
            return false;
        }
    }

    /*
        This is a method to check if a piece is a Black piece.
    */
    private Boolean checkWhiteOpponent(int newX, int newY) {
        Boolean opponent;
        Component c1 = chessBoard.findComponentAt(newX, newY);
        JLabel awaitingPiece = (JLabel) c1;
        String tmp1 = awaitingPiece.getIcon().toString();
        if (((tmp1.contains("Black")))) {
            opponent = true;
        } else {
            opponent = false;
        }
        return opponent;
    }

    private Boolean checkBlackOpponent(int newX, int newY) {
        Boolean opponent;
        Component c1 = chessBoard.findComponentAt(newX, newY);
        JLabel awaitingPiece = (JLabel) c1;
        String tmp1 = awaitingPiece.getIcon().toString();
        if (((tmp1.contains("White")))) {
            opponent = true;
        } else {
            opponent = false;
        }
        return opponent;
    }

    /*
        This method is called when we press the Mouse. So we need to find out what piece we have
        selected. We may also not have selected a piece!
    */
    public void mousePressed(MouseEvent e) {
        chessPiece = null;
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());
        if (c instanceof JPanel)
            return;

        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel) c;
        initialX = e.getX();
        initialY = e.getY();
        startX = (e.getX() / 75);
        startY = (e.getY() / 75);
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
    }

    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) return;
        chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
    }

    /*
       This method is used when the Mouse is released...we need to make sure the move was valid before
       putting the piece back on the board.
   */
    public void mouseReleased(MouseEvent e) {
        if (chessPiece == null) return;

        chessPiece.setVisible(false);
        Boolean whiteSuccess = false;
        Boolean blackSuccess = false;
        Boolean whiteGameWon = false;
        Boolean blackGameWon = false;
        Boolean progression = false;
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());
        String tmp = chessPiece.getIcon().toString();
        String pieceName = tmp.substring(0, (tmp.length() - 4));
        Boolean validMove = false;


        int landingX = (e.getX() / 75);
        int landingY = (e.getY() / 75);
        int xMovement = Math.abs((e.getX() / 75) - startX);
        int yMovement = Math.abs((e.getY() / 75) - startY);

		/*
			The only piece that has been enabled to move is a White Pawn...but we should really have this is a separate
			method somewhere...how would this work.
			
			So a Pawn is able to move two squares forward one its first go but only one square after that. 
			The Pawn is the only piece that cannot move backwards in chess...so be careful when committing 
			a pawn forward. A Pawn is able to take any of the opponent’s pieces but they have to be one 
			square forward and one square over, i.e. in a diagonal direction from the Pawns original position. 
			If a Pawn makes it to the top of the other side, the Pawn can turn into any other piece, for 
			demonstration purposes the Pawn here turns into a Queen.
		*/
        try {
            if (pieceName.equals("BlackPawn")) {
                if ((turn % 2) == 0) {
                    validMove = false;
                } else {
                    if (startY == 6) {
                        if (((yMovement == 1) || (yMovement == 2)) && (startY > landingY) && (xMovement == 0)) {
                            if (yMovement == 2) {
                                if (!piecePresent(e.getX(), e.getY()) && (!piecePresent(e.getX(), (e.getY() + 75)))) {
                                    validMove = true;
                                }
                            } else {
                                if (!piecePresent(e.getX(), e.getY())) {
                                    validMove = true;
                                }
                            }
                        } else if ((yMovement == 1) && (startY > landingY) && (xMovement == 1)) {
                            if (piecePresent(e.getX(), e.getY())) {
                                if (checkBlackOpponent(e.getX(), e.getY())) {
                                    validMove = true;
                                    if (checkWhiteKingTaken(e.getX(), e.getY())) {
                                        blackGameWon = true;
                                    }
                                }
                            }
                        }
                    } else {
                        if (((yMovement == 1)) && (startY > landingY) && (xMovement == 0)) {
                            if (!piecePresent(e.getX(), e.getY())) {
                                validMove = true;
                                if (landingY == 0) {
                                    blackSuccess = true;
                                }
                            }
                        } else if ((yMovement == 1) && (startY > landingY) && (xMovement == 1)) {
                            if (piecePresent(e.getX(), e.getY())) {
                                if (checkBlackOpponent(e.getX(), e.getY())) {
                                    validMove = true;
                                    if (landingY == 0) {
                                        blackSuccess = true;
                                    }

                                    if (checkWhiteKingTaken(e.getX(), e.getY())) {
                                        blackGameWon = true;
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (pieceName.equals("WhitePawn")) {
                if ((turn % 2) != 0) {
                    validMove = false;
                } else {
                    if (startY == 1) {
                        if (((yMovement == 1) || (yMovement == 2)) && (startY < landingY) && (xMovement == 0)) {
                            if (yMovement == 2) {
                                if (!piecePresent(e.getX(), e.getY()) && (!piecePresent(e.getX(), (e.getY() + 75)))) {
                                    validMove = true;
                                }
                            } else {
                                if (!piecePresent(e.getX(), e.getY())) {
                                    validMove = true;
                                }
                            }
                        } else if ((yMovement == 1) && (startY < landingY) && (xMovement == 1)) {
                            if (piecePresent(e.getX(), e.getY())) {
                                if (checkWhiteOpponent(e.getX(), e.getY())) {
                                    validMove = true;
                                    if (checkBlackKingTaken(e.getX(), e.getY())) {
                                        whiteGameWon = true;
                                    }
                                }
                            }
                        }
                    } else {
                        if (((yMovement == 1)) && (startY < landingY) && (xMovement == 0)) {
                            if (!piecePresent(e.getX(), e.getY())) {
                                validMove = true;
                                if (landingY == 7) {
                                    whiteSuccess = true;
                                }
                            }
                        } else if ((yMovement == 1) && (startY < landingY) && (xMovement == 1)) {
                            if (piecePresent(e.getX(), e.getY())) {
                                if (checkWhiteOpponent(e.getX(), e.getY())) {
                                    validMove = true;
                                    if (landingY == 7) {
                                        whiteSuccess = true;
                                    }

                                    if (checkBlackKingTaken(e.getX(), e.getY())) {
                                        whiteGameWon = true;
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (pieceName.contains("Knight")) {
                if (pieceName.contains("White") && ((turn % 2) != 0)) {
                    validMove = false;
                } else if (pieceName.contains("Black") && ((turn % 2) == 0)) {
                    validMove = false;
                } else {
                    if ((landingX > 7) || (landingY > 7)) {
                        validMove = false;
                    } else {
                        if (((xMovement == 1) && (yMovement == 2)) || ((yMovement == 1) && (xMovement == 2))) {
                            if (piecePresent(e.getX(), (e.getY()))) {
                                if (pieceName.contains("White")) {
                                    if (checkWhiteOpponent(e.getX(), e.getY())) {
                                        validMove = true;
                                        if (checkBlackKingTaken(e.getX(), e.getY())) {
                                            whiteGameWon = true;
                                        }
                                    } else {
                                        validMove = false;
                                    }
                                } else {
                                    if (checkBlackOpponent(e.getX(), e.getY())) {
                                        validMove = true;
                                        if (checkWhiteKingTaken(e.getX(), e.getY())) {
                                            blackGameWon = true;
                                        }
                                    } else {
                                        validMove = false;
                                    }
                                }
                            } else {
                                validMove = true;
                            }
                        }
                    }
                }
            } else if (pieceName.contains("Bishop")) {
                Boolean inTheWay = false;
                int distance = Math.abs(startX - landingX);
                if (pieceName.contains("White") && ((turn % 2) != 0)) {
                    validMove = false;
                } else if (pieceName.contains("Black") && ((turn % 2) == 0)) {
                    validMove = false;
                } else {
                    if ((landingX > 7) || (landingY > 7)) {
                        validMove = false;
                    } else {
                        if (Math.abs(startX - landingX) == Math.abs(startY - landingY)) {
                            if ((startX - landingX < 0) && (startY - landingY < 0)) {
                                for (int i = 0; i < distance; i++) {
                                    if (piecePresent(((initialX + (i * 75))), (initialY + (i * 75)))) {
                                        inTheWay = true;
                                    }
                                }
                            } else if ((startX - landingX < 0) && (startY - landingY > 0)) {
                                for (int i = 0; i < distance; i++) {
                                    if (piecePresent((initialX + (i * 75)), (initialY - (i * 75)))) {
                                        inTheWay = true;
                                    }
                                }
                            } else if ((startX - landingX > 0) && (startY - landingY > 0)) {
                                for (int i = 0; i < distance; i++) {
                                    if (piecePresent((initialX - (i * 75)), (initialY - (i * 75)))) {
                                        inTheWay = true;
                                    }
                                }
                            } else if ((startX - landingX > 0) && (startY - landingY < 0)) {
                                for (int i = 0; i < distance; i++) {
                                    if (piecePresent((initialX - (i * 75)), (initialY + (i * 75)))) {
                                        inTheWay = true;
                                    }
                                }
                            }
                            if (inTheWay) {
                                validMove = false;
                            } else {
                                if (piecePresent(e.getX(), (e.getY()))) {
                                    if (pieceName.contains("White")) {
                                        if (checkWhiteOpponent(e.getX(), e.getY())) {
                                            validMove = true;
                                            if (checkBlackKingTaken(e.getX(), e.getY())) {
                                                whiteGameWon = true;
                                            }
                                        } else {
                                            validMove = false;
                                        }
                                    } else {
                                        if (checkBlackOpponent(e.getX(), e.getY())) {
                                            validMove = true;
                                            if (checkWhiteKingTaken(e.getX(), e.getY())) {
                                                blackGameWon = true;
                                            }
                                        } else {
                                            validMove = false;
                                        }
                                    }
                                } else {
                                    validMove = true;
                                }
                            }
                        } else {
                            validMove = false;
                        }
                    }
                }
            } else if (pieceName.contains("Rook")) {
                Boolean inTheWay = false;
                if (pieceName.contains("White") && ((turn % 2) != 0)) {
                    validMove = false;
                } else if (pieceName.contains("Black") && ((turn % 2) == 0)) {
                    validMove = false;
                } else {
                    if ((landingX > 7) || (landingY > 7)) {
                        validMove = false;
                    } else {
                        if (((Math.abs(startX - landingX) != 0) && (Math.abs(startY - landingY) == 0)) || ((Math.abs(startX - landingX) == 0 && (landingY - startY) != 0))) {
                            if (Math.abs(startX - landingX) != 0) {
                                if (startX - landingX > 0) {
                                    for (int i = 0; i < xMovement; i++) {
                                        if (piecePresent(initialX - (i * 75), e.getY())) {
                                            inTheWay = true;
                                            break;
                                        } else {
                                            inTheWay = false;
                                        }
                                    }
                                } else {
                                    for (int i = 0; i < xMovement; i++) {
                                        if (piecePresent(initialX + (i * 75), e.getY())) {
                                            inTheWay = true;
                                            break;
                                        } else {
                                            inTheWay = false;
                                        }
                                    }
                                }
                            } else {
                                if (startY - landingY > 0) {
                                    for (int i = 0; i < yMovement; i++) {
                                        if (piecePresent(e.getX(), initialY - (i * 75))) {
                                            inTheWay = true;
                                            break;
                                        } else {
                                            inTheWay = false;
                                        }
                                    }
                                } else {
                                    for (int i = 0; i < yMovement; i++) {
                                        if (piecePresent(e.getX(), initialY + (i * 57))) {
                                            inTheWay = true;
                                            break;
                                        } else {
                                            inTheWay = false;
                                        }
                                    }
                                }
                            }
                            if (inTheWay) {
                                validMove = false;
                            } else {
                                if (piecePresent(e.getX(), (e.getY()))) {
                                    if (pieceName.contains("White")) {
                                        if (checkWhiteOpponent(e.getX(), e.getY())) {
                                            validMove = true;
                                            if (checkBlackKingTaken(e.getX(), e.getY())) {
                                                whiteGameWon = true;
                                            }
                                        } else {
                                            validMove = false;
                                        }
                                    } else {
                                        if (checkBlackOpponent(e.getX(), e.getY())) {
                                            validMove = true;
                                            if (checkWhiteKingTaken(e.getX(), e.getY())) {
                                                blackGameWon = true;
                                            }
                                        } else {
                                            validMove = false;
                                        }
                                    }
                                } else {
                                    validMove = true;
                                }
                            }
                        } else {
                            validMove = false;
                        }
                    }
                }
            } else if (pieceName.contains("Queen")) {
                Boolean inTheWay = false;
                int distance = Math.abs(startX - landingX);
                if (pieceName.contains("White") && ((turn % 2) != 0)) {
                    validMove = false;
                } else if (pieceName.contains("Black") && ((turn % 2) == 0)) {
                    validMove = false;
                } else {
                    if ((landingX > 7) || (landingY > 7)) {
                        validMove = false;
                    } else {
                        if (((Math.abs(startX - landingX) != 0) && (Math.abs(startY - landingY) == 0)) || ((Math.abs(startX - landingX) == 0 && (landingY - startY) != 0))) {
                            if (Math.abs(startX - landingX) != 0) {
                                if (startX - landingX > 0) {
                                    for (int i = 0; i < xMovement; i++) {
                                        if (piecePresent(initialX - (i * 75), e.getY())) {
                                            inTheWay = true;
                                            break;
                                        } else {
                                            inTheWay = false;
                                        }
                                    }
                                } else {
                                    for (int i = 0; i < xMovement; i++) {
                                        if (piecePresent(initialX + (i * 75), e.getY())) {
                                            inTheWay = true;
                                            break;
                                        } else {
                                            inTheWay = false;
                                        }
                                    }
                                }
                            } else {
                                if (startY - landingY > 0) {
                                    for (int i = 0; i < yMovement; i++) {
                                        if (piecePresent(e.getX(), initialY - (i * 75))) {
                                            inTheWay = true;
                                            break;
                                        } else {
                                            inTheWay = false;
                                        }
                                    }
                                } else {
                                    for (int i = 0; i < yMovement; i++) {
                                        if (piecePresent(e.getX(), initialY + (i * 57))) {
                                            inTheWay = true;
                                            break;
                                        } else {
                                            inTheWay = false;
                                        }
                                    }
                                }
                            }
                            if (inTheWay) {
                                validMove = false;
                            } else {
                                if (piecePresent(e.getX(), (e.getY()))) {
                                    if (pieceName.contains("White")) {
                                        if (checkWhiteOpponent(e.getX(), e.getY())) {
                                            validMove = true;
                                            if (checkBlackKingTaken(e.getX(), e.getY())) {
                                                whiteGameWon = true;
                                            }
                                        } else {
                                            validMove = false;
                                        }
                                    } else {
                                        if (checkBlackOpponent(e.getX(), e.getY())) {
                                            validMove = true;
                                            if (checkWhiteKingTaken(e.getX(), e.getY())) {
                                                blackGameWon = true;
                                            }
                                        } else {
                                            validMove = false;
                                        }
                                    }
                                } else {
                                    validMove = true;
                                }
                            }
                        } else if (Math.abs(startX - landingX) == Math.abs(startY - landingY)) {
                            if ((startX - landingX < 0) && (startY - landingY < 0)) {
                                for (int i = 0; i < distance; i++) {
                                    if (piecePresent(((initialX + (i * 75))), (initialY + (i * 75)))) {
                                        inTheWay = true;
                                    }
                                }
                            } else if ((startX - landingX < 0) && (startY - landingY > 0)) {
                                for (int i = 0; i < distance; i++) {
                                    if (piecePresent((initialX + (i * 75)), (initialY - (i * 75)))) {
                                        inTheWay = true;
                                    }
                                }
                            } else if ((startX - landingX > 0) && (startY - landingY > 0)) {
                                for (int i = 0; i < distance; i++) {
                                    if (piecePresent((initialX - (i * 75)), (initialY - (i * 75)))) {
                                        inTheWay = true;
                                    }
                                }
                            } else if ((startX - landingX > 0) && (startY - landingY < 0)) {
                                for (int i = 0; i < distance; i++) {
                                    if (piecePresent((initialX - (i * 75)), (initialY + (i * 75)))) {
                                        inTheWay = true;
                                    }
                                }
                            }
                            if (inTheWay) {
                                validMove = false;
                            } else {
                                if (piecePresent(e.getX(), (e.getY()))) {
                                    if (pieceName.contains("White")) {
                                        if (checkWhiteOpponent(e.getX(), e.getY())) {
                                            validMove = true;
                                            if (checkBlackOpponent(e.getX(), e.getY())) {
                                                whiteGameWon = true;
                                            }
                                        } else {
                                            validMove = false;
                                        }
                                    } else {
                                        if (checkBlackOpponent(e.getX(), e.getY())) {
                                            validMove = true;
                                            if (checkWhiteKingTaken(e.getX(), e.getY())) {
                                                blackGameWon = true;
                                            }
                                        } else {
                                            validMove = false;
                                        }
                                    }
                                } else {
                                    validMove = true;
                                }
                            }
                        } else {
                            validMove = false;
                        }
                    }
                }
            } else if (pieceName.contains("King")) {
                if (pieceName.contains("White") && ((turn % 2) != 0)) {
                    validMove = false;
                } else if (pieceName.contains("Black") && ((turn % 2) == 0)) {
                    validMove = false;
                } else {
                    if (checkKingPresent(e.getX(), (e.getY()))) {
                        validMove = false;
                    } else if ((landingX > 7) || (landingY > 7)) {
                        validMove = false;
                    } else if ((xMovement > 1) || (yMovement > 1)) {
                        validMove = false;
                    } else {
                        if (piecePresent(e.getX(), (e.getY()))) {
                            if (pieceName.contains("White")) {
                                if (checkWhiteOpponent(e.getX(), e.getY())) {
                                    validMove = true;
                                } else {
                                    validMove = false;
                                }
                            } else {
                                if (checkBlackOpponent(e.getX(), e.getY())) {
                                    validMove = true;
                                } else {
                                    validMove = false;
                                }
                            }
                        } else {
                            validMove = true;
                        }
                    }
                }
            }
            if (!validMove) {
                int location = 0;
                if (startY == 0) {
                    location = startX;
                } else {
                    location = (startY * 8) + startX;
                }
                String pieceLocation = pieceName + ".png";
                pieces = new JLabel(new ImageIcon(pieceLocation));
                panels = (JPanel) chessBoard.getComponent(location);
                panels.add(pieces);
            } else {
                if (whiteSuccess) {
                    int location = 56 + (e.getX() / 75);
                    if (c instanceof JLabel) {
                        Container parent = c.getParent();
                        parent.remove(0);
                        pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
                        parent = (JPanel) chessBoard.getComponent(location);
                        parent.add(pieces);
                    } else {
                        Container parent = (Container) c;
                        pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
                        parent = (JPanel) chessBoard.getComponent(location);
                        parent.add(pieces);
                    }

                } else if (blackSuccess) {
                    if (landingY == 0) {
                        int location = 0 + (e.getX() / 75);
                        if (c instanceof JLabel) {
                            Container parent = c.getParent();
                            parent.remove(0);
                            pieces = new JLabel(new ImageIcon("BlackQueen.png"));
                            parent = (JPanel) chessBoard.getComponent(location);
                            parent.add(pieces);
                        } else {
                            Container parent = (Container) c;
                            pieces = new JLabel(new ImageIcon("BlackQueen.png"));
                            parent = (JPanel) chessBoard.getComponent(location);
                            parent.add(pieces);
                        }
                    }

                } else {
                    if (c instanceof JLabel) {
                        Container parent = c.getParent();
                        parent.remove(0);
                        parent.add(chessPiece);
                    } else {
                        Container parent = (Container) c;
                        parent.add(chessPiece);
                    }
                    chessPiece.setVisible(true);
                }
                if ((xMovement > 0) || (yMovement > 0)) {
                    System.out.println("------------------------------------------------------------");
                    System.out.println("The piece that is being moved is: " + pieceName);
                    System.out.println("The starting coordinates are: " + " (" + startX + ", " + startY + ")");
                    System.out.println("The xMovement is: " + xMovement);
                    System.out.println("The yMovement is: " + yMovement);
                    System.out.println("The landing coordinates are: (" + landingX + ", " + landingY + ")");
                    System.out.println("------------------------------------------------------------");
                    turn ++;
                }
                if (whiteGameWon) {
                    JOptionPane.showMessageDialog(null, "White Wins!");
                    System.exit(0);
                }
                if (blackGameWon) {
                    JOptionPane.showMessageDialog(null, "Black Wins!");
                    System.exit(0);
                }
            }
        } catch (NullPointerException outOfBounds) {
            int location = 0;
            if (startY == 0) {
                location = startX;
            } else {
                location = (startY * 8) + startX;
            }
            String pieceLocation = pieceName + ".png";
            pieces = new JLabel(new ImageIcon(pieceLocation));
            panels = (JPanel) chessBoard.getComponent(location);
            panels.add(pieces);
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {

    }

    /*
        Main method that gets the ball moving.
    */
    public static void main(String[] args) {
        JFrame frame = new ChessProject();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}


