import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class VennDiagram extends JPanel {
    
private Ellipse2D.Double[] Ovalos;
private int cantidadDibujada;
public boolean U, A, B, C, D, AB, BC, CD, BD, AD, AC, ABC, BCD, ABD, ACD, ABCD;

    public VennDiagram(int cantidadDibujada){
        this.cantidadDibujada = cantidadDibujada;
        Ovalos = new Ellipse2D.Double[4];
        Ovalos[0] = new Ellipse2D.Double(0, 75, 250, 100);
        Ovalos[1] = new Ellipse2D.Double(150, 75, 250, 100);
        Ovalos[2] = new Ellipse2D.Double(50, 50, 250, 100);
        Ovalos[3] = new Ellipse2D.Double(100, 50, 250, 100);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f)); // Transparencia para ver las intersecciones
        g2d.setColor(Color.blue);
        for (int i = 0; i < cantidadDibujada; i++){
            // Establecer el grosor del contorno
            g2d.setStroke(new BasicStroke(2)); // Grosor de la línea del contorno
            
            // Establecer un color para el óvalo
            g2d.setColor(Color.BLUE);
            // Crear una transformación para inclinar el óvalo
            AffineTransform transformacion = new AffineTransform();
            // Mover el punto de rotación al centro del óvalo y rotarlo 45 grados
            transformacion.rotate(Math.toRadians(Math.pow(-1, i)*45), Ovalos[i].getCenterX(), Ovalos[i].getCenterY());

            // Aplicar la transformación
            g2d.setTransform(transformacion);

            // Dibujar el óvalo
            g2d.draw(Ovalos[i]);
            
            // Restaurar la transformación
            g2d.setTransform(new AffineTransform());
        }
        pintar(g2d);
    }
    
    private void pintar(Graphics2D g2d){
        switch(cantidadDibujada){
            case 1:
                if(U){
                    Rectangle2D rectangle = new Rectangle2D.Double(0, 0, getWidth(), getHeight());
                    Area area_u = new Area(rectangle);
                    
                    Area ovA = new Area(Ovalos[0]);
                    AffineTransform t_ovA = new AffineTransform();
                    t_ovA.rotate(Math.toRadians(45), Ovalos[0].getCenterX(), Ovalos[0].getCenterY());
                    ovA = new Area(ovA.createTransformedArea(t_ovA));
                    
                    area_u.subtract(ovA);
                    g2d.fill(area_u);
                }
                if(A){
                    Area ovA = new Area(Ovalos[0]);
                    AffineTransform t_ovA = new AffineTransform();
                    t_ovA.rotate(Math.toRadians(45), Ovalos[0].getCenterX(), Ovalos[0].getCenterY());
                    ovA = new Area(ovA.createTransformedArea(t_ovA));
                    g2d.fill(ovA);
                }
                break;
            case 2:
                if(U){
                    Rectangle2D rectangle = new Rectangle2D.Double(0, 0, getWidth(), getHeight());
                    Area area_u = new Area(rectangle);
                    
                    Area ovA = new Area(Ovalos[0]);
                    AffineTransform t_ovA = new AffineTransform();
                    t_ovA.rotate(Math.toRadians(45), Ovalos[0].getCenterX(), Ovalos[0].getCenterY());
                    ovA = new Area(ovA.createTransformedArea(t_ovA));
                    
                    Area ovB = new Area(Ovalos[1]);
                    AffineTransform t_ovB = new AffineTransform();
                    t_ovB.rotate(Math.toRadians(-45), Ovalos[1].getCenterX(), Ovalos[1].getCenterY());
                    ovB.transform(t_ovB);
                    
                    area_u.subtract(ovA);
                    area_u.subtract(ovB);
                    g2d.fill(area_u);
                }
                if(A){
                    Area ovA = new Area(Ovalos[0]);
                    AffineTransform t_ovA = new AffineTransform();
                    t_ovA.rotate(Math.toRadians(45), Ovalos[0].getCenterX(), Ovalos[0].getCenterY());
                    ovA = new Area(ovA.createTransformedArea(t_ovA));
                    
                    Area ovB = new Area(Ovalos[1]);
                    AffineTransform t_ovB = new AffineTransform();
                    t_ovB.rotate(Math.toRadians(-45), Ovalos[1].getCenterX(), Ovalos[1].getCenterY());
                    ovB.transform(t_ovB);
                    
                    ovA.subtract(ovB);
                    g2d.fill(ovA);
                }
                if(B){
                    Area ovA = new Area(Ovalos[0]);
                    AffineTransform t_ovA = new AffineTransform();
                    t_ovA.rotate(Math.toRadians(45), Ovalos[0].getCenterX(), Ovalos[0].getCenterY());
                    ovA = new Area(ovA.createTransformedArea(t_ovA));
                    
                    Area ovB = new Area(Ovalos[1]);
                    AffineTransform t_ovB = new AffineTransform();
                    t_ovB.rotate(Math.toRadians(-45), Ovalos[1].getCenterX(), Ovalos[1].getCenterY());
                    ovB.transform(t_ovB);
                    
                    ovB.subtract(ovA);
                    g2d.fill(ovB);
                }
                if(AB){
                    Area ovA = new Area(Ovalos[0]);
                    AffineTransform t_ovA = new AffineTransform();
                    t_ovA.rotate(Math.toRadians(45), Ovalos[0].getCenterX(), Ovalos[0].getCenterY());
                    ovA = new Area(ovA.createTransformedArea(t_ovA));
                    
                    Area ovB = new Area(Ovalos[1]);
                    AffineTransform t_ovB = new AffineTransform();
                    t_ovB.rotate(Math.toRadians(-45), Ovalos[1].getCenterX(), Ovalos[1].getCenterY());
                    ovB.transform(t_ovB);
                    
                    ovA.intersect(ovA);
                    g2d.fill(ovA);
                }
                break;
            case 3:
                if(U){
                    Rectangle2D rectangle = new Rectangle2D.Double(0, 0, getWidth(), getHeight());
                    Area area_u = new Area(rectangle);
                    
                    Area ovA = new Area(Ovalos[0]);
                    AffineTransform t_ovA = new AffineTransform();
                    t_ovA.rotate(Math.toRadians(45), Ovalos[0].getCenterX(), Ovalos[0].getCenterY());
                    ovA = new Area(ovA.createTransformedArea(t_ovA));
                    
                    Area ovB = new Area(Ovalos[1]);
                    AffineTransform t_ovB = new AffineTransform();
                    t_ovB.rotate(Math.toRadians(-45), Ovalos[1].getCenterX(), Ovalos[1].getCenterY());
                    ovB.transform(t_ovB);
                    
                    Area ovC = new Area(Ovalos[2]);
                    AffineTransform t_ovC = new AffineTransform();
                    t_ovC.rotate(Math.toRadians(45), Ovalos[2].getCenterX(), Ovalos[2].getCenterY());
                    ovC.transform(t_ovC);
                    
                    area_u.subtract(ovA);
                    area_u.subtract(ovB);
                    area_u.subtract(ovC);
                    g2d.fill(area_u);
                }
                if(A){
                    Area ovA = new Area(Ovalos[0]);
                    AffineTransform t_ovA = new AffineTransform();
                    t_ovA.rotate(Math.toRadians(45), Ovalos[0].getCenterX(), Ovalos[0].getCenterY());
                    ovA = new Area(ovA.createTransformedArea(t_ovA));
                    
                    Area ovB = new Area(Ovalos[1]);
                    AffineTransform t_ovB = new AffineTransform();
                    t_ovB.rotate(Math.toRadians(-45), Ovalos[1].getCenterX(), Ovalos[1].getCenterY());
                    ovB.transform(t_ovB);
                    
                    Area ovC = new Area(Ovalos[2]);
                    AffineTransform t_ovC = new AffineTransform();
                    t_ovC.rotate(Math.toRadians(45), Ovalos[2].getCenterX(), Ovalos[2].getCenterY());
                    ovC.transform(t_ovC);
                    
                    ovA.subtract(ovB);
                    ovA.subtract(ovC);
                    g2d.fill(ovA);
                }
                if(B){
                    Area ovA = new Area(Ovalos[0]);
                    AffineTransform t_ovA = new AffineTransform();
                    t_ovA.rotate(Math.toRadians(45), Ovalos[0].getCenterX(), Ovalos[0].getCenterY());
                    ovA = new Area(ovA.createTransformedArea(t_ovA));
                    
                    Area ovB = new Area(Ovalos[1]);
                    AffineTransform t_ovB = new AffineTransform();
                    t_ovB.rotate(Math.toRadians(-45), Ovalos[1].getCenterX(), Ovalos[1].getCenterY());
                    ovB.transform(t_ovB);
                    
                    Area ovC = new Area(Ovalos[2]);
                    AffineTransform t_ovC = new AffineTransform();
                    t_ovC.rotate(Math.toRadians(45), Ovalos[2].getCenterX(), Ovalos[2].getCenterY());
                    ovC.transform(t_ovC);
                    
                    ovB.subtract(ovA);
                    ovB.subtract(ovC);
                    g2d.fill(ovB);
                }
                if(C){
                    Area ovA = new Area(Ovalos[0]);
                    AffineTransform t_ovA = new AffineTransform();
                    t_ovA.rotate(Math.toRadians(45), Ovalos[0].getCenterX(), Ovalos[0].getCenterY());
                    ovA = new Area(ovA.createTransformedArea(t_ovA));
                    
                    Area ovB = new Area(Ovalos[1]);
                    AffineTransform t_ovB = new AffineTransform();
                    t_ovB.rotate(Math.toRadians(-45), Ovalos[1].getCenterX(), Ovalos[1].getCenterY());
                    ovB.transform(t_ovB);
                    
                    Area ovC = new Area(Ovalos[2]);
                    AffineTransform t_ovC = new AffineTransform();
                    t_ovC.rotate(Math.toRadians(45), Ovalos[2].getCenterX(), Ovalos[2].getCenterY());
                    ovC.transform(t_ovC);
                    
                    ovC.subtract(ovA);
                    ovC.subtract(ovB);
                    g2d.fill(ovC);
                }
                if(AB){
                    Area ovA = new Area(Ovalos[0]);
                    AffineTransform t_ovA = new AffineTransform();
                    t_ovA.rotate(Math.toRadians(45), Ovalos[0].getCenterX(), Ovalos[0].getCenterY());
                    ovA = new Area(ovA.createTransformedArea(t_ovA));
                    
                    Area ovB = new Area(Ovalos[1]);
                    AffineTransform t_ovB = new AffineTransform();
                    t_ovB.rotate(Math.toRadians(-45), Ovalos[1].getCenterX(), Ovalos[1].getCenterY());
                    ovB.transform(t_ovB);
                    
                    Area ovC = new Area(Ovalos[2]);
                    AffineTransform t_ovC = new AffineTransform();
                    t_ovC.rotate(Math.toRadians(45), Ovalos[2].getCenterX(), Ovalos[2].getCenterY());
                    ovC.transform(t_ovC);
                    
                    ovA.intersect(ovB);
                    ovA.subtract(ovC);
                    g2d.fill(ovA);
                }
                if(AC){
                    Area ovA = new Area(Ovalos[0]);
                    AffineTransform t_ovA = new AffineTransform();
                    t_ovA.rotate(Math.toRadians(45), Ovalos[0].getCenterX(), Ovalos[0].getCenterY());
                    ovA = new Area(ovA.createTransformedArea(t_ovA));
                    
                    Area ovB = new Area(Ovalos[1]);
                    AffineTransform t_ovB = new AffineTransform();
                    t_ovB.rotate(Math.toRadians(-45), Ovalos[1].getCenterX(), Ovalos[1].getCenterY());
                    ovB.transform(t_ovB);
                    
                    Area ovC = new Area(Ovalos[2]);
                    AffineTransform t_ovC = new AffineTransform();
                    t_ovC.rotate(Math.toRadians(45), Ovalos[2].getCenterX(), Ovalos[2].getCenterY());
                    ovC.transform(t_ovC);
                    
                    ovA.intersect(ovC);
                    ovA.subtract(ovB);
                    g2d.fill(ovA);
                }
                if(BC){
                    Area ovA = new Area(Ovalos[0]);
                    AffineTransform t_ovA = new AffineTransform();
                    t_ovA.rotate(Math.toRadians(45), Ovalos[0].getCenterX(), Ovalos[0].getCenterY());
                    ovA = new Area(ovA.createTransformedArea(t_ovA));
                    
                    Area ovB = new Area(Ovalos[1]);
                    AffineTransform t_ovB = new AffineTransform();
                    t_ovB.rotate(Math.toRadians(-45), Ovalos[1].getCenterX(), Ovalos[1].getCenterY());
                    ovB.transform(t_ovB);
                    
                    Area ovC = new Area(Ovalos[2]);
                    AffineTransform t_ovC = new AffineTransform();
                    t_ovC.rotate(Math.toRadians(45), Ovalos[2].getCenterX(), Ovalos[2].getCenterY());
                    ovC.transform(t_ovC);
                    
                    ovB.intersect(ovC);
                    ovB.subtract(ovA);
                    g2d.fill(ovB);
                }
                if(ABC){
                    Area ovA = new Area(Ovalos[0]);
                    AffineTransform t_ovA = new AffineTransform();
                    t_ovA.rotate(Math.toRadians(45), Ovalos[0].getCenterX(), Ovalos[0].getCenterY());
                    ovA = new Area(ovA.createTransformedArea(t_ovA));
                    
                    Area ovB = new Area(Ovalos[1]);
                    AffineTransform t_ovB = new AffineTransform();
                    t_ovB.rotate(Math.toRadians(-45), Ovalos[1].getCenterX(), Ovalos[1].getCenterY());
                    ovB.transform(t_ovB);
                    
                    Area ovC = new Area(Ovalos[2]);
                    AffineTransform t_ovC = new AffineTransform();
                    t_ovC.rotate(Math.toRadians(45), Ovalos[2].getCenterX(), Ovalos[2].getCenterY());
                    ovC.transform(t_ovC);
                    
                    ovA.intersect(ovB);
                    ovA.intersect(ovC);
                    g2d.fill(ovA);
                }
                break;
            case 4:
                if(U){
                    Rectangle2D rectangle = new Rectangle2D.Double(0, 0, getWidth(), getHeight());
                    Area area_u = new Area(rectangle);
                    
                    Area ovA = new Area(Ovalos[0]);
                    AffineTransform t_ovA = new AffineTransform();
                    t_ovA.rotate(Math.toRadians(45), Ovalos[0].getCenterX(), Ovalos[0].getCenterY());
                    ovA = new Area(ovA.createTransformedArea(t_ovA));
                    
                    Area ovB = new Area(Ovalos[1]);
                    AffineTransform t_ovB = new AffineTransform();
                    t_ovB.rotate(Math.toRadians(-45), Ovalos[1].getCenterX(), Ovalos[1].getCenterY());
                    ovB.transform(t_ovB);
                    
                    Area ovC = new Area(Ovalos[2]);
                    AffineTransform t_ovC = new AffineTransform();
                    t_ovC.rotate(Math.toRadians(45), Ovalos[2].getCenterX(), Ovalos[2].getCenterY());
                    ovC.transform(t_ovC);
                    
                    Area ovD = new Area(Ovalos[3]);
                    AffineTransform t_ovD = new AffineTransform();
                    t_ovD.rotate(Math.toRadians(-45), Ovalos[3].getCenterX(), Ovalos[3].getCenterY());
                    ovD.transform(t_ovD);
                    
                    area_u.subtract(ovA);
                    area_u.subtract(ovB);
                    area_u.subtract(ovC);
                    area_u.subtract(ovD);
                    g2d.fill(area_u);
                }
                if(A){
                    Area ovA = new Area(Ovalos[0]);
                    AffineTransform t_ovA = new AffineTransform();
                    t_ovA.rotate(Math.toRadians(45), Ovalos[0].getCenterX(), Ovalos[0].getCenterY());
                    ovA = new Area(ovA.createTransformedArea(t_ovA));
                    
                    Area ovB = new Area(Ovalos[1]);
                    AffineTransform t_ovB = new AffineTransform();
                    t_ovB.rotate(Math.toRadians(-45), Ovalos[1].getCenterX(), Ovalos[1].getCenterY());
                    ovB.transform(t_ovB);
                    
                    Area ovC = new Area(Ovalos[2]);
                    AffineTransform t_ovC = new AffineTransform();
                    t_ovC.rotate(Math.toRadians(45), Ovalos[2].getCenterX(), Ovalos[2].getCenterY());
                    ovC.transform(t_ovC);
                    
                    Area ovD = new Area(Ovalos[3]);
                    AffineTransform t_ovD = new AffineTransform();
                    t_ovD.rotate(Math.toRadians(-45), Ovalos[3].getCenterX(), Ovalos[3].getCenterY());
                    ovD.transform(t_ovD);
                    
                    ovA.subtract(ovB);
                    ovA.subtract(ovC);
                    ovA.subtract(ovD);
                    g2d.fill(ovA);
                }
                if(B){
                    Area ovA = new Area(Ovalos[0]);
                    AffineTransform t_ovA = new AffineTransform();
                    t_ovA.rotate(Math.toRadians(45), Ovalos[0].getCenterX(), Ovalos[0].getCenterY());
                    ovA = new Area(ovA.createTransformedArea(t_ovA));
                    
                    Area ovB = new Area(Ovalos[1]);
                    AffineTransform t_ovB = new AffineTransform();
                    t_ovB.rotate(Math.toRadians(-45), Ovalos[1].getCenterX(), Ovalos[1].getCenterY());
                    ovB.transform(t_ovB);
                    
                    Area ovC = new Area(Ovalos[2]);
                    AffineTransform t_ovC = new AffineTransform();
                    t_ovC.rotate(Math.toRadians(45), Ovalos[2].getCenterX(), Ovalos[2].getCenterY());
                    ovC.transform(t_ovC);
                    
                    Area ovD = new Area(Ovalos[3]);
                    AffineTransform t_ovD = new AffineTransform();
                    t_ovD.rotate(Math.toRadians(-45), Ovalos[3].getCenterX(), Ovalos[3].getCenterY());
                    ovD.transform(t_ovD);
                    
                    ovB.subtract(ovA);
                    ovB.subtract(ovC);
                    ovB.subtract(ovD);
                    g2d.fill(ovB);
                }
                if(C){
                    Area ovA = new Area(Ovalos[0]);
                    AffineTransform t_ovA = new AffineTransform();
                    t_ovA.rotate(Math.toRadians(45), Ovalos[0].getCenterX(), Ovalos[0].getCenterY());
                    ovA = new Area(ovA.createTransformedArea(t_ovA));
                    
                    Area ovB = new Area(Ovalos[1]);
                    AffineTransform t_ovB = new AffineTransform();
                    t_ovB.rotate(Math.toRadians(-45), Ovalos[1].getCenterX(), Ovalos[1].getCenterY());
                    ovB.transform(t_ovB);
                    
                    Area ovC = new Area(Ovalos[2]);
                    AffineTransform t_ovC = new AffineTransform();
                    t_ovC.rotate(Math.toRadians(45), Ovalos[2].getCenterX(), Ovalos[2].getCenterY());
                    ovC.transform(t_ovC);
                    
                    Area ovD = new Area(Ovalos[3]);
                    AffineTransform t_ovD = new AffineTransform();
                    t_ovD.rotate(Math.toRadians(-45), Ovalos[3].getCenterX(), Ovalos[3].getCenterY());
                    ovD.transform(t_ovD);
                    
                    ovC.subtract(ovA);
                    ovC.subtract(ovB);
                    ovC.subtract(ovD);
                    g2d.fill(ovC);
                }
                if(D){
                    Area ovA = new Area(Ovalos[0]);
                    AffineTransform t_ovA = new AffineTransform();
                    t_ovA.rotate(Math.toRadians(45), Ovalos[0].getCenterX(), Ovalos[0].getCenterY());
                    ovA = new Area(ovA.createTransformedArea(t_ovA));
                    
                    Area ovB = new Area(Ovalos[1]);
                    AffineTransform t_ovB = new AffineTransform();
                    t_ovB.rotate(Math.toRadians(-45), Ovalos[1].getCenterX(), Ovalos[1].getCenterY());
                    ovB.transform(t_ovB);
                    
                    Area ovC = new Area(Ovalos[2]);
                    AffineTransform t_ovC = new AffineTransform();
                    t_ovC.rotate(Math.toRadians(45), Ovalos[2].getCenterX(), Ovalos[2].getCenterY());
                    ovC.transform(t_ovC);
                    
                    Area ovD = new Area(Ovalos[3]);
                    AffineTransform t_ovD = new AffineTransform();
                    t_ovD.rotate(Math.toRadians(-45), Ovalos[3].getCenterX(), Ovalos[3].getCenterY());
                    ovD.transform(t_ovD);
                    
                    ovD.subtract(ovA);
                    ovD.subtract(ovB);
                    ovD.subtract(ovC);
                    g2d.fill(ovD);
                }
                if(AB){
                    Area ovA = new Area(Ovalos[0]);
                    AffineTransform t_ovA = new AffineTransform();
                    t_ovA.rotate(Math.toRadians(45), Ovalos[0].getCenterX(), Ovalos[0].getCenterY());
                    ovA = new Area(ovA.createTransformedArea(t_ovA));
                    
                    Area ovB = new Area(Ovalos[1]);
                    AffineTransform t_ovB = new AffineTransform();
                    t_ovB.rotate(Math.toRadians(-45), Ovalos[1].getCenterX(), Ovalos[1].getCenterY());
                    ovB.transform(t_ovB);
                    
                    Area ovC = new Area(Ovalos[2]);
                    AffineTransform t_ovC = new AffineTransform();
                    t_ovC.rotate(Math.toRadians(45), Ovalos[2].getCenterX(), Ovalos[2].getCenterY());
                    ovC.transform(t_ovC);
                    
                    Area ovD = new Area(Ovalos[3]);
                    AffineTransform t_ovD = new AffineTransform();
                    t_ovD.rotate(Math.toRadians(-45), Ovalos[3].getCenterX(), Ovalos[3].getCenterY());
                    ovD.transform(t_ovD);
                    
                    ovA.intersect(ovB);
                    ovA.subtract(ovC);
                    ovA.subtract(ovD);
                    g2d.fill(ovA);
                }
                if(AC){
                    Area ovA = new Area(Ovalos[0]);
                    AffineTransform t_ovA = new AffineTransform();
                    t_ovA.rotate(Math.toRadians(45), Ovalos[0].getCenterX(), Ovalos[0].getCenterY());
                    ovA = new Area(ovA.createTransformedArea(t_ovA));
                    
                    Area ovB = new Area(Ovalos[1]);
                    AffineTransform t_ovB = new AffineTransform();
                    t_ovB.rotate(Math.toRadians(-45), Ovalos[1].getCenterX(), Ovalos[1].getCenterY());
                    ovB.transform(t_ovB);
                    
                    Area ovC = new Area(Ovalos[2]);
                    AffineTransform t_ovC = new AffineTransform();
                    t_ovC.rotate(Math.toRadians(45), Ovalos[2].getCenterX(), Ovalos[2].getCenterY());
                    ovC.transform(t_ovC);
                    
                    Area ovD = new Area(Ovalos[3]);
                    AffineTransform t_ovD = new AffineTransform();
                    t_ovD.rotate(Math.toRadians(-45), Ovalos[3].getCenterX(), Ovalos[3].getCenterY());
                    ovD.transform(t_ovD);
                    
                    ovA.intersect(ovC);
                    ovA.subtract(ovB);
                    ovA.subtract(ovD);
                    g2d.fill(ovA);
                }
                if(AD){
                    Area ovA = new Area(Ovalos[0]);
                    AffineTransform t_ovA = new AffineTransform();
                    t_ovA.rotate(Math.toRadians(45), Ovalos[0].getCenterX(), Ovalos[0].getCenterY());
                    ovA = new Area(ovA.createTransformedArea(t_ovA));
                    
                    Area ovB = new Area(Ovalos[1]);
                    AffineTransform t_ovB = new AffineTransform();
                    t_ovB.rotate(Math.toRadians(-45), Ovalos[1].getCenterX(), Ovalos[1].getCenterY());
                    ovB.transform(t_ovB);
                    
                    Area ovC = new Area(Ovalos[2]);
                    AffineTransform t_ovC = new AffineTransform();
                    t_ovC.rotate(Math.toRadians(45), Ovalos[2].getCenterX(), Ovalos[2].getCenterY());
                    ovC.transform(t_ovC);
                    
                    Area ovD = new Area(Ovalos[3]);
                    AffineTransform t_ovD = new AffineTransform();
                    t_ovD.rotate(Math.toRadians(-45), Ovalos[3].getCenterX(), Ovalos[3].getCenterY());
                    ovD.transform(t_ovD);
                    
                    ovA.intersect(ovD);
                    ovA.subtract(ovB);
                    ovA.subtract(ovC);
                    g2d.fill(ovA);
                }
                if(BC){
                    Area ovA = new Area(Ovalos[0]);
                    AffineTransform t_ovA = new AffineTransform();
                    t_ovA.rotate(Math.toRadians(45), Ovalos[0].getCenterX(), Ovalos[0].getCenterY());
                    ovA = new Area(ovA.createTransformedArea(t_ovA));
                    
                    Area ovB = new Area(Ovalos[1]);
                    AffineTransform t_ovB = new AffineTransform();
                    t_ovB.rotate(Math.toRadians(-45), Ovalos[1].getCenterX(), Ovalos[1].getCenterY());
                    ovB.transform(t_ovB);
                    
                    Area ovC = new Area(Ovalos[2]);
                    AffineTransform t_ovC = new AffineTransform();
                    t_ovC.rotate(Math.toRadians(45), Ovalos[2].getCenterX(), Ovalos[2].getCenterY());
                    ovC.transform(t_ovC);
                    
                    Area ovD = new Area(Ovalos[3]);
                    AffineTransform t_ovD = new AffineTransform();
                    t_ovD.rotate(Math.toRadians(-45), Ovalos[3].getCenterX(), Ovalos[3].getCenterY());
                    ovD.transform(t_ovD);
                    
                    ovB.intersect(ovC);
                    ovB.subtract(ovA);
                    ovB.subtract(ovD);
                    g2d.fill(ovB);
                }
                if(BD){
                    Area ovA = new Area(Ovalos[0]);
                    AffineTransform t_ovA = new AffineTransform();
                    t_ovA.rotate(Math.toRadians(45), Ovalos[0].getCenterX(), Ovalos[0].getCenterY());
                    ovA = new Area(ovA.createTransformedArea(t_ovA));
                    
                    Area ovB = new Area(Ovalos[1]);
                    AffineTransform t_ovB = new AffineTransform();
                    t_ovB.rotate(Math.toRadians(-45), Ovalos[1].getCenterX(), Ovalos[1].getCenterY());
                    ovB.transform(t_ovB);
                    
                    Area ovC = new Area(Ovalos[2]);
                    AffineTransform t_ovC = new AffineTransform();
                    t_ovC.rotate(Math.toRadians(45), Ovalos[2].getCenterX(), Ovalos[2].getCenterY());
                    ovC.transform(t_ovC);
                    
                    Area ovD = new Area(Ovalos[3]);
                    AffineTransform t_ovD = new AffineTransform();
                    t_ovD.rotate(Math.toRadians(-45), Ovalos[3].getCenterX(), Ovalos[3].getCenterY());
                    ovD.transform(t_ovD);
                    
                    ovB.intersect(ovD);
                    ovB.subtract(ovA);
                    ovB.subtract(ovC);
                    g2d.fill(ovB);
                }
                if(CD){
                    Area ovA = new Area(Ovalos[0]);
                    AffineTransform t_ovA = new AffineTransform();
                    t_ovA.rotate(Math.toRadians(45), Ovalos[0].getCenterX(), Ovalos[0].getCenterY());
                    ovA = new Area(ovA.createTransformedArea(t_ovA));
                    
                    Area ovB = new Area(Ovalos[1]);
                    AffineTransform t_ovB = new AffineTransform();
                    t_ovB.rotate(Math.toRadians(-45), Ovalos[1].getCenterX(), Ovalos[1].getCenterY());
                    ovB.transform(t_ovB);
                    
                    Area ovC = new Area(Ovalos[2]);
                    AffineTransform t_ovC = new AffineTransform();
                    t_ovC.rotate(Math.toRadians(45), Ovalos[2].getCenterX(), Ovalos[2].getCenterY());
                    ovC.transform(t_ovC);
                    
                    Area ovD = new Area(Ovalos[3]);
                    AffineTransform t_ovD = new AffineTransform();
                    t_ovD.rotate(Math.toRadians(-45), Ovalos[3].getCenterX(), Ovalos[3].getCenterY());
                    ovD.transform(t_ovD);
                    
                    ovC.intersect(ovD);
                    ovC.subtract(ovA);
                    ovC.subtract(ovD);
                    g2d.fill(ovC);
                }
                if(ABC){
                    Area ovA = new Area(Ovalos[0]);
                    AffineTransform t_ovA = new AffineTransform();
                    t_ovA.rotate(Math.toRadians(45), Ovalos[0].getCenterX(), Ovalos[0].getCenterY());
                    ovA = new Area(ovA.createTransformedArea(t_ovA));
                    
                    Area ovB = new Area(Ovalos[1]);
                    AffineTransform t_ovB = new AffineTransform();
                    t_ovB.rotate(Math.toRadians(-45), Ovalos[1].getCenterX(), Ovalos[1].getCenterY());
                    ovB.transform(t_ovB);
                    
                    Area ovC = new Area(Ovalos[2]);
                    AffineTransform t_ovC = new AffineTransform();
                    t_ovC.rotate(Math.toRadians(45), Ovalos[2].getCenterX(), Ovalos[2].getCenterY());
                    ovC.transform(t_ovC);
                    
                    Area ovD = new Area(Ovalos[3]);
                    AffineTransform t_ovD = new AffineTransform();
                    t_ovD.rotate(Math.toRadians(-45), Ovalos[3].getCenterX(), Ovalos[3].getCenterY());
                    ovD.transform(t_ovD);
                    
                    ovA.intersect(ovB);
                    ovA.intersect(ovC);
                    ovA.subtract(ovD);
                    g2d.fill(ovA);
                }
                if(ABD){
                    Area ovA = new Area(Ovalos[0]);
                    AffineTransform t_ovA = new AffineTransform();
                    t_ovA.rotate(Math.toRadians(45), Ovalos[0].getCenterX(), Ovalos[0].getCenterY());
                    ovA = new Area(ovA.createTransformedArea(t_ovA));
                    
                    Area ovB = new Area(Ovalos[1]);
                    AffineTransform t_ovB = new AffineTransform();
                    t_ovB.rotate(Math.toRadians(-45), Ovalos[1].getCenterX(), Ovalos[1].getCenterY());
                    ovB.transform(t_ovB);
                    
                    Area ovC = new Area(Ovalos[2]);
                    AffineTransform t_ovC = new AffineTransform();
                    t_ovC.rotate(Math.toRadians(45), Ovalos[2].getCenterX(), Ovalos[2].getCenterY());
                    ovC.transform(t_ovC);
                    
                    Area ovD = new Area(Ovalos[3]);
                    AffineTransform t_ovD = new AffineTransform();
                    t_ovD.rotate(Math.toRadians(-45), Ovalos[3].getCenterX(), Ovalos[3].getCenterY());
                    ovD.transform(t_ovD);
                    
                    ovA.intersect(ovB);
                    ovA.intersect(ovD);
                    ovA.subtract(ovC);
                    g2d.fill(ovA);
                }
                if(ACD){
                    Area ovA = new Area(Ovalos[0]);
                    AffineTransform t_ovA = new AffineTransform();
                    t_ovA.rotate(Math.toRadians(45), Ovalos[0].getCenterX(), Ovalos[0].getCenterY());
                    ovA = new Area(ovA.createTransformedArea(t_ovA));
                    
                    Area ovB = new Area(Ovalos[1]);
                    AffineTransform t_ovB = new AffineTransform();
                    t_ovB.rotate(Math.toRadians(-45), Ovalos[1].getCenterX(), Ovalos[1].getCenterY());
                    ovB.transform(t_ovB);
                    
                    Area ovC = new Area(Ovalos[2]);
                    AffineTransform t_ovC = new AffineTransform();
                    t_ovC.rotate(Math.toRadians(45), Ovalos[2].getCenterX(), Ovalos[2].getCenterY());
                    ovC.transform(t_ovC);
                    
                    Area ovD = new Area(Ovalos[3]);
                    AffineTransform t_ovD = new AffineTransform();
                    t_ovD.rotate(Math.toRadians(-45), Ovalos[3].getCenterX(), Ovalos[3].getCenterY());
                    ovD.transform(t_ovD);
                    
                    ovA.intersect(ovC);
                    ovA.intersect(ovD);
                    ovA.subtract(ovB);
                    g2d.fill(ovA);
                }
                if(BCD){
                    Area ovA = new Area(Ovalos[0]);
                    AffineTransform t_ovA = new AffineTransform();
                    t_ovA.rotate(Math.toRadians(45), Ovalos[0].getCenterX(), Ovalos[0].getCenterY());
                    ovA = new Area(ovA.createTransformedArea(t_ovA));
                    
                    Area ovB = new Area(Ovalos[1]);
                    AffineTransform t_ovB = new AffineTransform();
                    t_ovB.rotate(Math.toRadians(-45), Ovalos[1].getCenterX(), Ovalos[1].getCenterY());
                    ovB.transform(t_ovB);
                    
                    Area ovC = new Area(Ovalos[2]);
                    AffineTransform t_ovC = new AffineTransform();
                    t_ovC.rotate(Math.toRadians(45), Ovalos[2].getCenterX(), Ovalos[2].getCenterY());
                    ovC.transform(t_ovC);
                    
                    Area ovD = new Area(Ovalos[3]);
                    AffineTransform t_ovD = new AffineTransform();
                    t_ovD.rotate(Math.toRadians(-45), Ovalos[3].getCenterX(), Ovalos[3].getCenterY());
                    ovD.transform(t_ovD);
                    
                    ovB.intersect(ovC);
                    ovB.intersect(ovD);
                    ovB.subtract(ovA);
                    g2d.fill(ovB);
                }
                if(ABCD){
                    Area ovA = new Area(Ovalos[0]);
                    AffineTransform t_ovA = new AffineTransform();
                    t_ovA.rotate(Math.toRadians(45), Ovalos[0].getCenterX(), Ovalos[0].getCenterY());
                    ovA = new Area(ovA.createTransformedArea(t_ovA));
                    
                    Area ovB = new Area(Ovalos[1]);
                    AffineTransform t_ovB = new AffineTransform();
                    t_ovB.rotate(Math.toRadians(-45), Ovalos[1].getCenterX(), Ovalos[1].getCenterY());
                    ovB.transform(t_ovB);
                    
                    Area ovC = new Area(Ovalos[2]);
                    AffineTransform t_ovC = new AffineTransform();
                    t_ovC.rotate(Math.toRadians(45), Ovalos[2].getCenterX(), Ovalos[2].getCenterY());
                    ovC.transform(t_ovC);
                    
                    Area ovD = new Area(Ovalos[3]);
                    AffineTransform t_ovD = new AffineTransform();
                    t_ovD.rotate(Math.toRadians(-45), Ovalos[3].getCenterX(), Ovalos[3].getCenterY());
                    ovD.transform(t_ovD);
                    
                    ovA.intersect(ovB);
                    ovA.intersect(ovC);
                    ovA.intersect(ovD);
                    g2d.fill(ovA);
                }
                break;
        }
    }

    public static void main(String[] args) {
        int cantidad = 4;
        JFrame frame = new JFrame("Diagrama de Venn de " + cantidad + " conjuntos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        VennDiagram venn = new VennDiagram(cantidad);
        venn.U = true;
        //venn.A = true;
        //venn.B = true;
        //venn.C = true;
        //venn.D = true;
        //venn.AB = true;
        //venn.AC = true;
        //venn.AD = true;
        //venn.BC = true;
        //venn.BD = true;
        //venn.CD = true;
        //venn.ABC = true;
        //venn.ABD = true;
        //venn.ACD = true;
        //venn.BCD = true;
        //venn.ABCD = true;
        frame.add(venn);
        frame.setVisible(true);
    }
}
