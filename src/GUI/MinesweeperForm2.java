/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import InternalLogic.GameControl;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;

/**
 *
 *  Joseph Zhong
 *  Minesweeper - Java (v2.85)
 *  This program is the GUI Object for my overall Minesweeper Project
 *  Minesweeper - GUI Object
 *  1 April 2014
 *
 */

public class MinesweeperForm2 extends JFrame
{
    // GUI Variables declaration
    private JMenuItem EasyButton;
    private JMenuItem HardButton;
    private JMenuItem MediumButton;

    private JLabel MessageLabel;

    private StringBuilder sb;

    private JMenu NewGameMenu;
    private JMenuItem QuitButton;
    private JFrame mainFrame;
    private JMenu HelpMenu;
    private JMenu FileMenu;
    private JMenuBar jMenuBar1;
    private JPanel mainPanel;
    // End of GUI variables declaration

    // visual stuff
    private static final int EASY_X = 500;
    private static final int EASY_Y = 500;

    private static final int MEDIUM_X = 700;
    private static final int MEDIUM_Y = 700;

    private static final int HARD_X = 1024;
    private static final int HARD_Y = 640;

    private static Dimension FrameSize;
    private static Dimension PanelSize;

    private static GameControl MainManager;
    private static MinesweeperButton[][] ButtonGrid;

    private static boolean isGridConstructed;

    // simul clicky stuff
    private static final int B1DM = MouseEvent.BUTTON1_DOWN_MASK;
    private static final int B3DM = MouseEvent.BUTTON3_DOWN_MASK;
    private boolean bothWereDown;
    private int flaggedNeighbors;

    // winning stuff
    private int safeButtonsLeft;
    private int counter;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Bull Excrements Code">
    private void initComponents()
    {
        mainFrame = new JFrame();

        sb = new StringBuilder(64);

        sb.append("<html>"
                + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + "Welcome to MiArcade (v0.0.2)! "
                + "<br>Click on File to get started, or Help for more information."
                + "<br><br>"
                + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + "Keyboard Shortcuts: F2 for New Game</html>");

        MessageLabel = new JLabel(sb.toString());

        mainPanel = new JPanel();
        jMenuBar1 = new JMenuBar();
        FileMenu = new JMenu();
        NewGameMenu = new JMenu();
        EasyButton = new JMenuItem();
        MediumButton = new JMenuItem();
        HardButton = new JMenuItem();
        QuitButton = new JMenuItem();
        HelpMenu = new JMenu();

        // frame things
        GroupLayout mainFrameLayout = new GroupLayout(mainFrame.getContentPane());
        mainFrame.getContentPane().setLayout(mainFrameLayout);
        mainFrameLayout.setHorizontalGroup(
            mainFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        mainFrameLayout.setVerticalGroup(
            mainFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                formMouseReleased(evt);
            }
        });
        addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent evt)
            {
                formKeyPressed(evt);
            }
        });

        mainPanel.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent evt)
            {
                mainPanelKeyPressed(evt);
            }
        });

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(40, 60, 80)
                .addComponent(MessageLabel)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(MessageLabel)
                .addContainerGap(200, Short.MAX_VALUE))
        );

        FileMenu.setText("File");
        FileMenu.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                FileMenuMouseClicked(evt);
            }
        });

        NewGameMenu.setText("New Game");

        EasyButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
        EasyButton.setText("Easy");
        EasyButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                EasyButtonMouseReleased(evt);
            }
        });
        NewGameMenu.add(EasyButton);


        MediumButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
        MediumButton.setText("Medium");
        MediumButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                MediumButtonMouseReleased(evt);
            }
        });
        MediumButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent evt)
            {
                MediumButtonActionPerformed(evt);
            }
        });
        NewGameMenu.add(MediumButton);

        HardButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
        HardButton.setText("Hard");
        HardButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                HardButtonMouseReleased(evt);
            }
        });
        HardButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent evt)
            {
                HardButtonActionPerformed(evt);
            }
        });
        NewGameMenu.add(HardButton);

        FileMenu.add(NewGameMenu);

        QuitButton.setText("Quit");
        QuitButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                QuitButtonMouseReleased(evt);
            }
        });
        FileMenu.add(QuitButton);

        jMenuBar1.add(FileMenu);

        HelpMenu.setText("Help");
        jMenuBar1.add(HelpMenu);

        setJMenuBar(jMenuBar1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>

    /**
     * Creates new form MinesweeperGUI
     */
    public MinesweeperForm2()
    {
        isGridConstructed = false;
        bothWereDown = false;
        initComponents();
        this.setTitle("Minesweeper v2");

        JLabel label = new JLabel(sb.toString());
        add(label);

        setVisible(true);
        repaint();
    }

    private String instructions()
    {
        String instructions = "\t\tWelcome to MiArcade (v0.0.2)! "
                + "\nClick on File to get started, or Help for more information."
                + "\n\n\t Keyboard Shortcuts: F2 for New Game";
        return instructions;
    }



    /**
     * Exit method.
     *  Exits the program as does Alt+F4 or Clicking the Red X in the upper right
     *  corner.
     * @param evt User Mouse Click release.
     */
    private void QuitButtonMouseReleased(MouseEvent evt) {
        // TODO add your handling code here:
        this.dispose();
    }

    /**
     * Constructor Method.
     * Constructs the easy grid.
     * @param evt
     */

    private void EasyButtonMouseReleased(MouseEvent evt)
    {
        if(isGridConstructed)
        {
            mainPanel.removeAll();
            constructMinesweeper("easy");
        }
        else
        {
            isGridConstructed = true;
            mainPanel.removeAll();
            constructMinesweeper("easy");
        }
    }

    private void constructMinesweeper(String difficulty)
    {
        MainManager = new GameControl(difficulty);
        safeButtonsLeft = MainManager.getMainGrid().getLength(true)
                * MainManager.getMainGrid().getLength(false)
                - MainManager.getMainGrid().getCurrentSetting().getMines();

        ButtonGrid = new MinesweeperButton[MainManager.getMainGrid().getLength(false)][MainManager.getMainGrid().getLength(true)];
       // produce a GUI grid

        for(int y = 0; y < MainManager.getMainGrid().getLength(false); y++)
        {
            for(int x = 0; x < MainManager.getMainGrid().getLength(true); x++)
            {
                ButtonGrid[y][x] = new MinesweeperButton(" ");
                MouseListener MouseClick;
                MouseMotionListener MouseMove;

                MouseMove = new MouseMotionListener()
                {
                    @Override
                    public void mouseDragged(MouseEvent e)
                    {
                        //throw new UnsupportedOperationException("Not supported yet.");
                        //System.out.println("Button detected dragging: " + e.getX() + ", " + e.getY());
                        //if ( /* if pointer leaves field where mouse was pressed */)
                        {
                            // some other code
                            bothWereDown = false;
                            // some other code
                        }
                    }

                    @Override
                    public void mouseMoved(MouseEvent e)
                    {
                        //throw new UnsupportedOperationException("Not supported yet.");
                        //System.out.println("Button detected movement: " + e.getX() + ", " + e.getY());
                    }

                };

                MouseClick =  new MouseListener()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        if(SwingUtilities.isLeftMouseButton(e))
                        {
                            System.out.println("Left click Successful");
                        }
                        else if(SwingUtilities.isRightMouseButton(e))
                        {
                            System.out.println("Right click Succesfful");
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e)
                    {
                        /*
                        AbstractButton abstractButton = (AbstractButton) e.getSource();
                        abstractButton.getModel().setArmed(true);
                        abstractButton.getModel().setPressed(true);
                        * */
                        System.out.println("Press Successful");
                        int both = B1DM | B3DM;
                        bothWereDown = (e.getModifiersEx() & both) == both;

                        if (bothWereDown)
                        {
                            // action if both buttons pressed
                            int r = getButtonCoordinates(e)[0];
                            int c = getButtonCoordinates(e)[1];

                            //makeNeighborCoordinates(r, c);
                            int [] row_neighbor = new int [8];
                            int [] col_neighbor = new int [8];

                            row_neighbor[0] = r + 1;
                            col_neighbor[0] = c;
                            row_neighbor[1] = r + 1;
                            col_neighbor[1] = c + 1;
                            row_neighbor[2] = r;
                            col_neighbor[2] = c + 1;
                            row_neighbor[3] = r - 1;
                            col_neighbor[3] = c + 1;
                            row_neighbor[4] = r - 1;
                            col_neighbor[4] = c;
                            row_neighbor[5] = r - 1;
                            col_neighbor[5] = c - 1;
                            row_neighbor[6] = r;
                            col_neighbor[6] = c - 1;
                            row_neighbor[7] = r + 1;
                            col_neighbor[7] = c - 1;

                            flaggedNeighbors = 0;

                            for(int s = 0; s < 8; s++) // iterate all sides
                            {
                                if (row_neighbor[s] >= 0 && row_neighbor[s] < ButtonGrid.length       // within y
                                    && col_neighbor[s] >= 0 && col_neighbor[s] < ButtonGrid[1].length // within x
                                    && !ButtonGrid[row_neighbor[s]][col_neighbor[s]].isSelected()
                                    && ButtonGrid[row_neighbor[s]][col_neighbor[s]].getIsFlagged())
                                {
                                    flaggedNeighbors++;
                                }
                                else if(row_neighbor[s] >= 0 && row_neighbor[s] < ButtonGrid.length       // within y
                                    && col_neighbor[s] >= 0 && col_neighbor[s] < ButtonGrid[1].length // within x
                                    && !ButtonGrid[row_neighbor[s]][col_neighbor[s]].isSelected())
                                {
                                    ButtonGrid[row_neighbor[s]][col_neighbor[s]].getModel().setArmed(true);
                                    ButtonGrid[row_neighbor[s]][col_neighbor[s]].getModel().setPressed(true);
                                }
                            }

                            System.out.println("Flagged Neighbors: " + flaggedNeighbors);
                            System.out.println("Simultaneous click successful");
                        }
                    }

                    @Override
                    public void mouseReleased(MouseEvent e)
                    {
                        AbstractButton abstractButton = (AbstractButton) e.getSource();

                        int y = 0; int x = 0;
                        outerloop:
                        for(y = 0; y < ButtonGrid.length; y++)
                        {
                            for(x = 0; x < ButtonGrid[1].length; x++)
                            {
                                if(abstractButton.equals(ButtonGrid[y][x]))
                                {
                                    break outerloop;
                                    // coordinates saved
                                }
                            }
                        }
                        System.out.println(y + " " + x);

                        // prepare special icons
                        ImageIcon FlagIcon;
                        FlagIcon = new ImageIcon("C://Users/Joseph/Downloads/GitHub/Outside/2013/Minesweeper/src/Images/FlagImage.png");
                        ImageIcon MineIcon;
                        MineIcon = new ImageIcon("C://Users/Joseph/Downloads/GitHub/Outside/2013/Minesweeper/src/Images/MineImage.png");

                        // prepare resize
                        Image MineImage = MineIcon.getImage(); // transform it
                        Image FlagImage = FlagIcon.getImage();

                        int maxSize = Math.max(abstractButton.getHeight(), abstractButton.getWidth()) / 2;

                        Image rescaledImage;
                        ImageIcon imageIcon;

                        int bothMask = MouseEvent.BUTTON1_DOWN_MASK | MouseEvent.BUTTON3_DOWN_MASK;
                        if (bothWereDown)
                        {
                            System.out.println("Both down");
                            int row = getButtonCoordinates(e)[0];
                            int col = getButtonCoordinates(e)[1];

                            int [] row_neighbor = new int [8];
                            int [] col_neighbor = new int [8];

                            row_neighbor[0] = row+1;
                            col_neighbor[0] = col;
                            row_neighbor[1] = row+1;
                            col_neighbor[1] = col+1;
                            row_neighbor[2] = row;
                            col_neighbor[2] = col+1;
                            row_neighbor[3] = row-1;
                            col_neighbor[3] = col+1;
                            row_neighbor[4] = row-1;
                            col_neighbor[4] = col;
                            row_neighbor[5] = row-1;
                            col_neighbor[5] = col-1;
                            row_neighbor[6] = row;
                            col_neighbor[6] = col-1;
                            row_neighbor[7] = row+1;
                            col_neighbor[7] = col-1;

                            MinesweeperButton saveTypingButton;
                            for(int s = 0; s < 8; s++)
                            {
                                if(row_neighbor[s] >= 0 && row_neighbor[s] < ButtonGrid.length
                                    && col_neighbor[s] >= 0 && col_neighbor[s] < ButtonGrid[1].length
                                    && !(saveTypingButton = ButtonGrid[row_neighbor[s]][col_neighbor[s]]).getIsFlagged()
                                    /*&& !saveTypingButton.isSelected()*/
                                    && flaggedNeighbors == Integer.parseInt(ButtonGrid[y][x].getText()))
                                {

                                    MainManager.getMainGrid().selectBox(row_neighbor[s], col_neighbor[s]);
                                    //safeButtonsLeft--;
                                    System.out.println("Safe Buttons Left: " + safeButtonsLeft);
                                    String displayText = MainManager.getMainGrid().getDisplay(row_neighbor[s], col_neighbor[s]);
                                    if(displayText.equals("9"))
                                    {
                                        rescaledImage = MineImage.getScaledInstance(maxSize, maxSize, Image.SCALE_SMOOTH); // scale it the smooth way
                                        imageIcon = new ImageIcon(rescaledImage);  // transform it back

                                        JLabel test1 = new JLabel(imageIcon);

                                        saveTypingButton.setBackground(Color.red);
                                        saveTypingButton.add(test1);

                                        // initiate losing
                                        losing();
                                    }
                                    saveTypingButton.setText(displayText);

                                    resetFont(displayText, row_neighbor[s], col_neighbor[s]);
                                }
                            }
                        }

                        // right click flag
                        if(SwingUtilities.isRightMouseButton(e)
                                && !SwingUtilities.isLeftMouseButton(e)
                                && !abstractButton.isSelected())
                        {
                            if(!ButtonGrid[y][x].getIsFlagged())
                            {
                                rescaledImage = FlagImage.getScaledInstance(maxSize, maxSize, Image.SCALE_SMOOTH);
                                imageIcon = new ImageIcon(rescaledImage);
                                JLabel iconLabel = new JLabel(imageIcon);
                                abstractButton.add(iconLabel);
                                ButtonGrid[y][x].setIsFlagged(true);
                                MainManager.getMainGrid().flagBox(y, x);
                                //safeButtonsLeft--;
                                System.out.println("Safe Buttons Left: " + safeButtonsLeft);
                                resetFont("", y, x);
                            }
                            else
                            {
                                ButtonGrid[y][x].setIsFlagged(false);
                                MainManager.getMainGrid().flagBox(y, x);
                                //safeButtonsLeft--;
                                System.out.println("Safe Buttons Left: " + safeButtonsLeft);
                                ButtonGrid[y][x].removeAll();
                                resetFont("", y, x);
                            }
                        }
                        else if(SwingUtilities.isLeftMouseButton(e)
                                && !SwingUtilities.isRightMouseButton(e)
                                && !ButtonGrid[y][x].getIsFlagged())
                        {
                            // leftClick
                            MainManager.getMainGrid().selectBox(y, x);
                            //safeButtonsLeft--;
                            System.out.println("Safe Buttons Left: " + safeButtonsLeft);
                            String displayText = MainManager.getMainGrid().getDisplay(y, x);
                            if(displayText.equals("9"))
                            {
                                rescaledImage = MineImage.getScaledInstance(maxSize, maxSize, Image.SCALE_SMOOTH); // scale it the smooth way
                                imageIcon = new ImageIcon(rescaledImage);  // transform it back

                                JLabel test1 = new JLabel(imageIcon);

                                abstractButton.setBackground(Color.red);
                                abstractButton.add(test1);

                                //intiate losing
                                losing();
                            }
                            abstractButton.setText(displayText);

                            resetFont(displayText, y, x);

                            //break; // apparently that made all the differnece lol
                        }
                        else
                        {
                            abstractButton.setSelected(false);
                        }

                        // update everything
                        for(int r = 0; r < ButtonGrid.length; r++)
                        {
                            for(int c = 0; c < ButtonGrid[1].length; c++)
                            {
                                // check for zeros
                                String displayText = MainManager.getMainGrid().getDisplay(r, c);
                                if(!(displayText.equals("_"))
                                        && !displayText.equals("!"))
                                {
                                    ButtonGrid[r][c].setSelected(true);
                                    ButtonGrid[r][c].setText(displayText);


                                    //safeButtonsLeft--;
                                    //System.out.println("Safe Buttons Left: " + safeButtonsLeft);

                                    resetFont(displayText, r, c);
                                }
                                ButtonGrid[r][c].getModel().setArmed(false);
                                ButtonGrid[r][c].getModel().setPressed(false);
                            }
                        }
                        update();
                        requestFocusInWindow(); // doesn't work
                    }

                    @Override
                    public void mouseEntered(MouseEvent e)
                    {
                        //throw new UnsupportedOperationException("Not supported yet.");
                        AbstractButton abstractButton = (AbstractButton) e.getSource();
                        abstractButton.getModel().setArmed(true);
                    }

                    @Override
                    public void mouseExited(MouseEvent e)
                    {
                        //throw new UnsupportedOperationException("Not supported yet.");
                        AbstractButton abstractButton = (AbstractButton) e.getSource();
                        //abstractButton.getModel().setArmed(false);
                        //abstractButton.getModel().setPressed(false);
                    }
                };

                ButtonGrid[y][x].addMouseListener(MouseClick);
                ButtonGrid[y][x].addMouseMotionListener(MouseMove);
                mainPanel.add(ButtonGrid[y][x]);

                System.out.println(y + ", " + x); // debug
            }
        } // end for loop

        // sets panel and frame
        this.setResizable(false); // MUST PUT THIS LINE BEFORE BOUNDS AND SIZES ARE SET

        FrameSize = new Dimension(EASY_X, EASY_Y);
        PanelSize = new Dimension(EASY_X, EASY_Y);

        this.setSize(FrameSize);
        this.setLocation(300, 100);

        mainPanel.setPreferredSize(PanelSize);

        mainPanel.setLayout(new GridLayout(MainManager.getMainGrid().getLength(true), MainManager.getMainGrid().getLength(false)));
        this.setVisible(true);

        this.pack();
    }

    private void losing()
    {
        for(int r = 0; r < ButtonGrid.length; r++)
        {
            for(int c = 0; c < ButtonGrid[1].length; c++)
            {
                if(MainManager.getMainGrid().getMines(r, c) == 9)
                {
                    ButtonGrid[r][c].setSelected(true);
                     // prepare special icons

                    ImageIcon MineIcon;
                    MineIcon = new ImageIcon("C://Users/Joseph/Downloads/GitHub/Outside/2013/Minesweeper/src/Images/MineImage.png");

                    // prepare resize
                    Image MineImage = MineIcon.getImage(); // transform it

                    int maxSize = Math.max(ButtonGrid[r][c].getHeight(), ButtonGrid[r][c].getWidth()) / 2;

                    Image rescaledImage;
                    ImageIcon imageIcon;

                    rescaledImage = MineImage.getScaledInstance(maxSize, maxSize, Image.SCALE_SMOOTH); // scale it the smooth way
                    imageIcon = new ImageIcon(rescaledImage);  // transform it back

                    JLabel test1 = new JLabel(imageIcon);

                    ButtonGrid[r][c].add(test1);
                    //ButtonGrid[r][c].repaint();
                    resetFont("", r, c);
                }
            }
        }

        JOptionPane.showMessageDialog(rootPane, "You lose. Sorry."
                + "\n╔╦╦╦╦╦╦╦╦╦╦╦╦╗"
                + "\n╠╬╬╬╬╬╬╬╬╬╬╬╬╣"
                + "\n╠╬╬╬╬╬╬╬╬╬╬╬╬╣"
                + "\n╠╬╬█╬╬╬╬╬╬█╬╬╣"
                + "\n╠╬╬╬╬╬╬╬╬╬╬╬╬╣"
                + "\n╠╬╬╬╬╬╬╬╬╬╬╬╬╣"
                + "\n╠╬╬╬╬╬╬╬╬╬╬╬╬╣"
                + "\n╠╬██████████╬╣"
                + "\n╠╬█╬╬╬╬╬╬╬╬█╬╣"
                + "\n╚╩╩╩╩╩╩╩╩╩╩╩╩╝", "☹", JOptionPane.YES_NO_CANCEL_OPTION);
        //System.out.println("You lost by flagging incorrectly");
        System.out.println("You lose");
    }

    private void makeNeighborCoordinates(int row, int col)
    {
        int [] row_neighbor = new int [8];
        int [] col_neighbor = new int [8];

        row_neighbor[0] = row+1;
        col_neighbor[0] = col;
        row_neighbor[1] = row+1;
        col_neighbor[1] = col+1;
        row_neighbor[2] = row;
        col_neighbor[2] = col+1;
        row_neighbor[3] = row-1;
        col_neighbor[3] = col+1;
        row_neighbor[4] = row-1;
        col_neighbor[4] = col;
        row_neighbor[5] = row-1;
        col_neighbor[5] = col-1;
        row_neighbor[6] = row;
        col_neighbor[6] = col-1;
        row_neighbor[7] = row+1;
        col_neighbor[7] = col-1;
    }

    private int[] getButtonCoordinates(MouseEvent e)
    {
        AbstractButton abstractButton = (AbstractButton) e.getSource();
        ArrayList<Object> MouseClickCollection = new ArrayList<>();

        int y; int x = 0;
        outerloop:
        for(y = 0; y < ButtonGrid.length; y++)
        {
            for(x = 0; x < ButtonGrid[1].length; x++)
            {
                if(abstractButton.equals(ButtonGrid[y][x]))
                {
                    break outerloop;
                    // coordinates saved
                }
            }
        }

        int[] coordinates = {y, x};
        return coordinates;
    }

    private void leftClick(MouseEvent e)
    {

    }

     private void resetFont(String displayNumber, int _r, int _c)
     {
         int height = ButtonGrid[_r][_c].getHeight() / 2;
         Font numberFont = (new Font("sansserif", Font.BOLD, height));

         ButtonGrid[_r][_c].setFont(numberFont);

         if(displayNumber.equals("9"))
         {
             ButtonGrid[_r][_c].setForeground(new Color(255, 0, 0)); // red
         }
         else
         {
             ButtonGrid[_r][_c].setForeground(new Color(0, 130, 200)); // 0,130,200 is a pretty and solid cyan blue
         }

         ButtonGrid[_r][_c].repaint();
     }

     private void update()
     {
         counter = 0;
         for(int r = 0; r < ButtonGrid.length; r++)
         {
             for(int c = 0; c < ButtonGrid[1].length; c++)
             {
                 if(ButtonGrid[r][c].isSelected())
                 {
                     counter++;
                 }
                 System.out.println("Safe Buttons: " + (safeButtonsLeft - counter));
             }
         }

         if(counter == safeButtonsLeft)
         {
             JOptionPane.showMessageDialog(rootPane, "You won!"
                     + "\n╔══╗░░░░╔╦╗░░╔═════╗"
                     + "\n║╚═╬════╬╣╠═╗║░▀░▀░║"
                     + "\n╠═╗║╔╗╔╗║║║╩╣║╚═══╝║"
                     + "\n╚══╩╝╚╝╚╩╩╩═╝╚═════╝", "Smileys  ☺  ☻  ت ヅ  ツ  ッ  シ Ü  ϡ  ﭢ", JOptionPane.YES_NO_CANCEL_OPTION);
         }
     }

    private void MediumButtonMouseReleased(MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void MediumButtonActionPerformed(ActionEvent evt)
    {
        // ASDFASDF;ALSF;ALKDA;LSDFJA;SFD
    }

    private void HardButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:

    }

    private void HardButtonMouseReleased(MouseEvent evt) {
        // TODO add your handling code here:
        MainManager = new GameControl("hard");
    }

    private void formKeyPressed(KeyEvent evt)
    {
        // TODO add your handling code here:
        checkF2Key(evt);
        //checkTestCMD(evt);
        System.out.println("Form detected a key press: " + evt.getKeyLocation() + ": " + evt.getKeyCode() + " - " + evt.getKeyChar());

    }

    private void formMouseReleased(MouseEvent evt)
    {
        // TODO add your handling code here:
        System.out.println("Mouse Click Coordinates: " + evt.getX() + ", " + evt.getY());
    }

    private void mainPanelKeyPressed(KeyEvent evt) {
        // TODO add your handling code here:
        //checkF2Key(evt);
        //checkTestCMD(evt);
    }

    private void FileMenuMouseClicked(MouseEvent evt) {
        // TODO add your handling code here:

        System.out.println("FileMenu Clicked");
    }

    private void checkF2Key(KeyEvent evt)
    {
        if(evt.getKeyCode() == 113)
        {
            System.out.println(evt.getKeyCode());
            EasyButtonMouseReleased(null);
            //FileMenuMouseClicked(null);
        }
    }

    private void checkTestCMD(KeyEvent evt)
    {
        if(evt.getKeyCode() == 114)
        {
            System.out.println(evt.getKeyCode());
            mainPanel.removeAll();
            constructMinesweeper("test");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        // Set Nimbus and Feel
        try
        {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(MinesweeperForm2.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            Logger.getLogger(MinesweeperForm2.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            Logger.getLogger(MinesweeperForm2.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (UnsupportedLookAndFeelException ex)
        {
            Logger.getLogger(MinesweeperForm2.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new MinesweeperForm2().setVisible(true);
            }
        });
    }
}