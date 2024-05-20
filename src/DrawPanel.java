import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class DrawPanel extends JPanel implements KeyListener, MouseListener, ActionListener {

    private ArrayList<Card> hand;
    private Rectangle button;
    private JButton entry;

    public DrawPanel() {
        button = new Rectangle(72, 225, 160, 26);
        this.addMouseListener(this);
        hand = Card.buildHand();
        entry = new JButton("Replace Cards");
        entry.addActionListener(this);
        add(entry);
        entry.setFocusable(false);
        addKeyListener((KeyListener) this);
        addMouseListener(this);
        setFocusable(true); // this line of code + one below makes this panel active for keylistener events
        requestFocusInWindow(); // see comment above
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 50;
        int y = 10;
        entry.setLocation(88,265);
        for (int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                Card c = hand.get(i * 3 + j);
                if (c.getHighlight()) {
                    g.drawRect(x, y, c.getImage().getWidth(), c.getImage().getHeight());
                }
                c.setRectangleLocation(x, y);
                g.drawImage(c.getImage(), x, y, null);
                x = x + c.getImage().getWidth() + 10;
            }
            x = 50;
            y += 75;
        }
        g.setFont(new Font("Courier New", Font.BOLD, 20));
        g.drawString("GET NEW CARDS", 75, 245);
        g.drawRect((int)button.getX(), (int)button.getY(), (int)button.getWidth(), (int)button.getHeight());
    }

    public void mousePressed(MouseEvent e) {

        Point clicked = e.getPoint();

        if (e.getButton() == 1) {
            if (button.contains(clicked)) {
                hand = Card.buildHand();
            }

            for (int i = 0; i < hand.size(); i++) {
                Rectangle box = hand.get(i).getCardBox();
                if (box.contains(clicked)) {
                    hand.get(i).flipCard();
                }
            }
        }

        if (e.getButton() == 3) {
            for (int i = 0; i < hand.size(); i++) {
                Rectangle box = hand.get(i).getCardBox();
                if (box.contains(clicked)) {
                    hand.get(i).flipHighlight();
                }
            }
        }


    }

    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
