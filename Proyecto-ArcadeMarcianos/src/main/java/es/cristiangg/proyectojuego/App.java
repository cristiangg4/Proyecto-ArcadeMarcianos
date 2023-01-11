package es.cristiangg.proyectojuego;

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class App extends Application {
    int cantidad = 0;
    int finalizado = 0;
   
///////////posiciones de la nave
    double posX=210;
    double posY =500;
   
//////////posicion disparo
    double posXdisparo= posX;
    double posYdisparo=490;
   
/////////movimiento nave
    int velocidad = 0;
   
////////movimiento marciano
    int marciano1X = 50;
    int marciano2X = 100;  
    int marciano3X = 150;
    int marciano4X = 200;
   
//////// rebotes marciano
    int movimientoMarciano1X = 3;
   
/////////imagenes de fondo
    int imagenY= 0;
    int imagen2Y = -689;
   
////////dimensiones de pantalla
    final int SCENE_TAM_X = 460;
    final int SCENE_TAM_Y = 689;
    final int STICK_WIDTH = 7;
    final int STICK_HEIGHT = 50;
   
//////////velocidad disparo
    int velocidadisparo= 0;
    Rectangle disparo = new Rectangle ();
   
///////////variable random
    Random random = new Random();
   
/////////movimiento meteorito
    int moviMeteoritoY = 0;
    int caidaMarcianoY = 0;
    int moviMeteoritoY1 =0;
    int moviMeteoritoY2 =0;
    int moviMeteoritoX = random.nextInt(460);
    int moviMeteoritoX1 = random.nextInt(460);
    int moviMeteoritoX2 = random.nextInt(460);

    int ESCENA = 450;
   
/////////////////////////Imagenes de fondo//////////////////////////////////
   
///////////posicion de las imagenes de fondo
    Image img = new Image(getClass().getResourceAsStream("/imagenes/estrellas.jpeg"));
    Image img2 = new Image(getClass().getResourceAsStream("/imagenes/estrellas2.jpeg"));
   
//////////////////////Imagenes Marciano/////////////////////////////////////
   
//////////////imagenes de marciano 1
    Image marciano = new Image(getClass().getResourceAsStream("/imagenes/verde.png"));
    ImageView imageView1 = new ImageView(marciano);
   
//////////imagen de marciano 2
    Image marciano1 = new Image(getClass().getResourceAsStream("/imagenes/verde.png"));
    ImageView imageView2 = new ImageView(marciano1);
   
//////////imagen marciano 3
    Image marciano2 = new Image(getClass().getResourceAsStream("/imagenes/verde.png"));
    ImageView imageView3 = new ImageView(marciano2);
   
////////////imagen marciano 4
    Image marciano3 = new Image(getClass().getResourceAsStream("/imagenes/verde.png"));
    ImageView imageView4 = new ImageView(marciano3);
   
/////////////////////////Imagenes Meteorito/////////////////////////////////
   
//////////////meteorito1
    Image meteorito = new Image(getClass().getResourceAsStream("/imagenes/meteorito.png"));
    ImageView imageView5 = new ImageView(meteorito);
   
////////////meteorito2
    Image meteorito2 = new Image(getClass().getResourceAsStream("/imagenes/meteorito.png"));
    ImageView imageView6 = new ImageView(meteorito2);
   
////////////meteorito3
    Image meteorito3 = new Image(getClass().getResourceAsStream("/imagenes/meteorito.png"));
    ImageView imageView7 = new ImageView(meteorito3);

   
//////////variable puntuaciones
    final int TEXT_SIZE = 20;
    int puntuacion;
   
///////////Grupos meteoritos
    Group grupo5 = new Group();
    Group grupo6 = new Group();
    Group grupo7 = new Group();

    @Override  
    public void start(Stage stage) {
       
//////////////////////////////////Pantalla/////////////////////////////////////////////
                   
////////////dimensiones de la pantalla
        Pane root = new Pane();
        var scene = new Scene(root, 460, 689);
        stage.setScene(scene);
        stage.show();
       
////////////introduccion de la imagen de fondo
        ImageView imgView = new ImageView(img);
        root.getChildren().add(imgView);
       
        ImageView imgView2 = new ImageView(img2);
        root.getChildren().add(imgView2);

//////////////////////Texto Pantalla/////////////////////////////////

//////////////Puntuaciones
        HBox paneScores = new HBox();
        paneScores.setTranslateY(20);
        paneScores.setMinWidth(ESCENA);
        paneScores.setAlignment(Pos.CENTER);
        paneScores.setSpacing(100);
        root.getChildren().add(paneScores);
///////////////Puntuacion Actual      
        HBox paneCurrentScore = new HBox();
        paneCurrentScore.setSpacing(10);
        paneScores.getChildren().add(paneCurrentScore);
//////////////Etiqueta Puntuacion      
        Text textTitleScore = new Text ("Puntuacion:");
        textTitleScore.setFont(Font.font(TEXT_SIZE));
        textTitleScore.setFill(Color.WHITE);
////////////Sobre Puntuacion      
        Text textScore = new Text ("0");
        textScore.setFont(Font.font(TEXT_SIZE));
        textScore.setFill(Color.WHITE);
        /////////////////////////////////
        Text textTitleScore2 = new Text ("HAS GANADO!!! PRESIONA R");
        textTitleScore2.setFont(Font.font(TEXT_SIZE));
        textTitleScore2.setFill(Color.WHITE);
        textTitleScore2.setVisible(false);
        /////////////////////////////////
        paneCurrentScore.getChildren().add(textTitleScore);
        paneCurrentScore.getChildren().add(textScore);
        paneCurrentScore.getChildren().add(textTitleScore2);


//////////////////////////////////Audio Juego//////////////////////////////////

//URL urlAudio = getClass().getResource("/audio/archivoAudio.mp3");
//if(urlAudio != null) {
//    try {
//        AudioClip audioClip1 = new AudioClip(urlAudio.toURI().toString());
//        audioClip1.play();
//    } catch (URISyntaxException ex) {
//        System.out.println("Error en el formato de ruta de archivo de audio");
//    }            
//} else {
//    System.out.println("No se ha encontrado el archivo de audio");
//}

//////////////////////////////////////Marcianos///////////////////////////////
       
/////////////////marciano 1
        Rectangle zona1 = new Rectangle(35, 35);
        zona1.setX(103);
        zona1.setY(80);
        Group grupo1 = new Group();
        grupo1.getChildren().addAll(imageView1, zona1);
        root.getChildren().add(grupo1);
        zona1.setVisible(false);
        grupo1.setLayoutY(50);
        imageView1.setScaleY(0.15);
        imageView1.setScaleX(0.15);
       
/////////////marciano 2
        Rectangle zona2 = new Rectangle(35, 35);
        zona2.setX(105);
        zona2.setY(80);
        Group grupo2 = new Group();
        grupo2.getChildren().addAll(imageView2, zona2);
        root.getChildren().add(grupo2);
        zona2.setVisible(false);
        grupo2.setLayoutY(50);
        imageView2.setScaleY(0.15);
        imageView2.setScaleX(0.15);
       
////////////////// marciano 3
        Rectangle zona3 = new Rectangle(35, 35);
        zona3.setX(107);
        zona3.setY(80);
        Group grupo3 = new Group();
        grupo3.getChildren().addAll(imageView3, zona3);
        root.getChildren().add(grupo3);
        zona3.setVisible(false);
        grupo3.setLayoutY(50);
        imageView3.setScaleY(0.15);
        imageView3.setScaleX(0.15);
       
////////////////marciano 4
        Rectangle zona4 = new Rectangle(35, 35);
        zona4.setX(109);
        zona4.setY(80);
        Group grupo4 = new Group();
        grupo4.getChildren().addAll(imageView4, zona4);
        root.getChildren().add(grupo4);
        zona4.setVisible(false);
        grupo4.setLayoutY(50);
        imageView4.setScaleY(0.15);
        imageView4.setScaleX(0.15);
       
   
/////////////////////////////creacion de la nave////////////////////////////////
               
/////////////pico de la nave
        Polyline cabeza = new Polyline();
        cabeza.getPoints().addAll(new Double[]{
            20.0, 20.0,
            -20.0, 20.0,
            0.0, 0.0 });
        cabeza.setFill(Color.YELLOW);
       
/////////////ala derecha de la nave
        Polyline derecha = new Polyline();
        derecha.getPoints().addAll(new Double[]{
            20.0, 30.0,
            50.0, 70.0,
            20.0, 70.0 });
        derecha.setFill(Color.BLUE);
       
///////////ala izquierda de la nave
        Polyline izquierda = new Polyline();
        izquierda.getPoints().addAll(new Double[]{
            -20.0, 30.0,
            -50.0, 70.0,
            -20.0, 70.0 });
        izquierda.setFill(Color.BLUE);
       
//////////cuerpo de la nave
        Rectangle cuerpo = new Rectangle ();
        cuerpo.setWidth(40);
        cuerpo.setHeight(40);
        cuerpo.setX(-20);
        cuerpo.setY(20);
        cuerpo.setFill(Color.GREEN);

/////////////circulo de la ventana de la nave
        Circle circleBall = new Circle (0,40,7);
        circleBall.setFill(Color.BLUE);
        root.getChildren().add(circleBall);
       
///////////fuego derecho
        Polyline fuegod = new Polyline();
        fuegod.getPoints().addAll(new Double[]{
            0.0, 60.0,
            20.0, 60.0,
            10.0, 100.0 });
        fuegod.setFill(Color.RED);
       
////////////fuego izquierdo
        Polyline fuegoi = new Polyline();
        fuegoi.getPoints().addAll(new Double[]{
            0.0, 60.0,
            -20.0, 60.0,
            -10.0, 100.0 });
        fuegoi.setFill(Color.RED);
       
/////////////disparo nave
        disparo.setWidth(5);
        disparo.setHeight(10);
        disparo.setFill(Color.BLUE);
        root.getChildren().add(disparo);

////////////agrupar las partes de la nave
        Group groupPersonaje = new Group();
        groupPersonaje.getChildren().add(cabeza);
        groupPersonaje.getChildren().add(cuerpo);
        groupPersonaje.getChildren().add(derecha);
        groupPersonaje.getChildren().add(izquierda);
        groupPersonaje.getChildren().add(circleBall);
        groupPersonaje.getChildren().add(fuegod);
        groupPersonaje.getChildren().add(fuegoi);
        root.getChildren().add(groupPersonaje);
       
///////////////////////////////Meteoritos///////////////////////////////////////////////
       
/////////////meteorito1
        Circle circlemeteorito = new Circle (0,0,12);
        circlemeteorito.setLayoutX(50);
        circlemeteorito.setLayoutY(10);
        circlemeteorito.setFill(Color.RED);  
        grupo5.getChildren().addAll(imageView5, circlemeteorito);
        root.getChildren().add(grupo5);
        circlemeteorito.setVisible(false);
        imageView5.setLayoutX(-100);
        imageView5.setLayoutY(-120);
        imageView5.setScaleY(0.10);
        imageView5.setScaleX(0.10);
        grupo5.setLayoutX(moviMeteoritoX);
       
//////////////meteorito2
        Circle circlemeteorito2 = new Circle (0,0,12);
        circlemeteorito2.setLayoutX(300);
        circlemeteorito2.setLayoutY(10);
        circlemeteorito2.setFill(Color.RED);
        grupo6.getChildren().addAll(imageView6, circlemeteorito2);
        root.getChildren().add(grupo6);
        circlemeteorito2.setVisible(false);
        imageView6.setLayoutX(150);
        imageView6.setLayoutY(-120);
        imageView6.setScaleY(0.10);
        imageView6.setScaleX(0.10);
        grupo6.setLayoutX(moviMeteoritoX1);

       
///////////////meteorito3
        Circle circlemeteorito3 = new Circle (0,0,12);
        circlemeteorito3.setLayoutX(300);
        circlemeteorito3.setLayoutY(10);
        circlemeteorito3.setFill(Color.RED);
        grupo7.getChildren().addAll(imageView7, circlemeteorito3);
        root.getChildren().add(grupo7);
        circlemeteorito3.setVisible(false);
        imageView7.setLayoutX(150);
        imageView7.setLayoutY(-120);
        imageView7.setScaleY(0.10);
        imageView7.setScaleX(0.10);
        grupo7.setLayoutX(moviMeteoritoX2);


             
//////////////////////////////////////Movimiento teclado//////////////////////////////////

////////////// movimiento del teclado
        scene.setOnKeyPressed((KeyEvent event) -> {
        switch(event.getCode()) {    
            case LEFT:
                velocidad = -2;
                if (puntuacion >= 1){
                    velocidad = -4;
                }
                if (puntuacion >= 2){
                    velocidad = -6;
                }
                if (puntuacion >= 3){
                    velocidad = -8;
                }
                if (puntuacion >= 4){
                    velocidad = -10;
                }
                break;
            case RIGHT:
                velocidad = 2;
                if (puntuacion >= 1){
                    velocidad = 4;
                }
                if (puntuacion >= 2){
                    velocidad = 6;
                }
                if (puntuacion >= 3){
                    velocidad = 8;
                }
                if (puntuacion >= 4){
                    velocidad = 10;
                }
                break;
            case SPACE:
                velocidadisparo -= 5;
                break;
            case R:
                grupo1.setLayoutY(50);
                grupo1.setLayoutX(50);
                grupo2.setLayoutY(50);
                grupo2.setLayoutX(100);
                grupo3.setLayoutY(50);
                grupo3.setLayoutX(150);
                grupo4.setLayoutY(50);
                grupo4.setLayoutX(200);
                puntuacion = 0;
                textScore.setText(String.valueOf(puntuacion));
                textTitleScore2.setVisible(false);
        }
        });

        Timeline animationespacio = new Timeline(
               
//////////////////////////movimiento de la imagen de fondo///////////////////////////

            new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {
                System.out.println("primera imagen " + imagenY);
                imgView.setY(imagenY);
                imagenY += 5;
               
                System.out.println("segunda imagen " + imagenY);
                imgView2.setY(imagen2Y);
                imagen2Y += 5;
               
                if (imagenY > 680) {
                    imagenY = -675;
                }
               
                if (imagen2Y > 680) {
                    imagen2Y = -675;
                }
               
///////////////////desplazar figuras
                groupPersonaje.setLayoutX(posX);
                groupPersonaje.setLayoutY(posY);
               
//////////////////diparo nave
                disparo.setX(posX);
                disparo.setY(posYdisparo);

       
/////////////////suma de la posicion X y velocidad de la nave
                posX += velocidad;
                if(posX < 50){        
                    posX = 50;
                }else{
                    if(posX > SCENE_TAM_X - STICK_HEIGHT) {
                        posX = SCENE_TAM_X - STICK_HEIGHT;
                    }
                }
                       
/////////////////////velocidad disparo de la nave
                if (velocidadisparo == -5){
                    posYdisparo = posYdisparo -5;
                }
               
                //volver disparo a la nave
                if (posYdisparo == 0){
                    posYdisparo = 490;
                    velocidadisparo = 0;
                }
                   
                   
///////////////////////////Movimiento marcianos////////////////////////////////

////////////////movimiento marciano1
            grupo1.setLayoutX(marciano1X);
            marciano1X += movimientoMarciano1X;
            if(marciano4X >= 300) {
                movimientoMarciano1X = -3;
            }
            if(marciano1X <= -120) {
                movimientoMarciano1X = 3;
            }
           
///////////////////marciano 2
            grupo2.setLayoutX(marciano2X);
            marciano2X += movimientoMarciano1X;
       
////////////////// marciano 3
            grupo3.setLayoutX(marciano3X);
            marciano3X += movimientoMarciano1X;
                System.out.println(velocidadisparo);

///////////////////// marciano 4
            grupo4.setLayoutX(marciano4X);
            marciano4X += movimientoMarciano1X;
           
//////////////////////////////////Colision marcianos///////////////////////////

            Shape.intersect(zona1, disparo);
                Shape Colision1 = Shape.intersect(zona1, disparo);
                boolean colisionPrimera = Colision1.getBoundsInLocal().isEmpty();
                if (colisionPrimera == false) {
                    grupo1.setLayoutY(-5000);
                    grupo1.setLayoutX(-5000);
                    puntuacion++;
                    textScore.setText(String.valueOf(puntuacion));
                    finalizado ++;
                }
            Shape.intersect(zona2, disparo);
                Shape Colision2 = Shape.intersect(zona2, disparo);
                boolean colisionSegunda = Colision2.getBoundsInLocal().isEmpty();
                if (colisionSegunda == false) {
                    grupo2.setLayoutY(-5000);
                    grupo2.setLayoutX(-5000);
                    puntuacion++;
                    textScore.setText(String.valueOf(puntuacion));
                    finalizado ++;

                }
            Shape.intersect(zona3, disparo);
                Shape Colision3 = Shape.intersect(zona3, disparo);
                boolean colisionTercera = Colision3.getBoundsInLocal().isEmpty();
                if (colisionTercera == false) {
                    grupo3.setLayoutY(-5000);
                    grupo3.setLayoutX(-5000);
                    puntuacion++;
                    textScore.setText(String.valueOf(puntuacion));
                    finalizado ++;

                }
            Shape.intersect(zona4, disparo);
                Shape Colision4 = Shape.intersect(zona4, disparo);
                boolean colisionCuarta = Colision4.getBoundsInLocal().isEmpty();
                if (colisionCuarta == false) {
                    grupo4.setLayoutY(-5000);
                    grupo4.setLayoutX(-5000);
                    puntuacion++;
                    textScore.setText(String.valueOf(puntuacion));
                    finalizado ++;

                }
               
                if (finalizado == 4){
                    textTitleScore2.setVisible(true);

                }
               
                if (finalizado == 0){
                    textTitleScore2.setVisible(false);
                }

            System.out.println(finalizado);

//////////////////Movimientos de meteorito//////////////////////////////////

/////////////caida meteorito1
            moviMeteoritoY +=5;
            grupo5.setLayoutY(moviMeteoritoY);
            System.out.println("Caida 1 Meteorito" +moviMeteoritoY);
 
//////////////caida meteorito2
            moviMeteoritoY1 +=8;
            grupo6.setLayoutY(moviMeteoritoY1);
            System.out.println("Caida 2 Meteorito" + moviMeteoritoY1);

//////////////////caida meteorito3
            moviMeteoritoY2 +=3;
            grupo7.setLayoutY(moviMeteoritoY2);
            System.out.println("Caida 3 Meteorito" + moviMeteoritoY2);
           
////////////////////Repeticion del Meteorito///////////////////////////////

//////////////////Meteorito Primero
            if (moviMeteoritoY == 680) {
                int valor = random.nextInt(400);
                System.out.println("Colocando meteorito arriba");
                moviMeteoritoY = 0;
                grupo5.setLayoutY(moviMeteoritoY);
                grupo5.setLayoutX (valor);

            };

/////////////////Meteorito Segundo
            if (moviMeteoritoY1 == 680) {
                int valor = random.nextInt(400);
                moviMeteoritoY1 = 0;
                grupo6.setLayoutY (moviMeteoritoY1);
                grupo6.setLayoutX (valor);
            };
//////////////////Meteorito Tercero
            if (moviMeteoritoY2 == 680) {
                int valor = random.nextInt(400);
                moviMeteoritoY2 = 0;
                grupo7.setLayoutY (moviMeteoritoY2);
                grupo7.setLayoutX (valor);
            };

//////////////////////////Colision del meteorito 1///////////////////////////////

//////////////////Ala izquierda
            Shape.intersect(circlemeteorito, izquierda);
                Shape ColisionMeteorito = Shape.intersect(circlemeteorito, izquierda);
                boolean colisionMeteorito = ColisionMeteorito.getBoundsInLocal().isEmpty();
                if (colisionMeteorito == false) {
                   puntuacion = 0;
                   textScore.setText(String.valueOf(puntuacion));
                   grupo1.setLayoutY(50);
                   grupo1.setLayoutX(50);
                   grupo2.setLayoutY(50);
                   grupo2.setLayoutX(100);
                   grupo3.setLayoutY(50);
                   grupo3.setLayoutX(150);
                   grupo4.setLayoutY(50);
                   grupo4.setLayoutX(200);
                   finalizado = 0;
                }
                 
/////////////////////Ala derecha
            Shape.intersect(circlemeteorito, derecha);
                Shape ColisionMeteorito1 = Shape.intersect(circlemeteorito, derecha);
                boolean colisionMeteorito1 = ColisionMeteorito1.getBoundsInLocal().isEmpty();
                if (colisionMeteorito1 == false) {
                   puntuacion = 0;
                   textScore.setText(String.valueOf(puntuacion));
                   grupo1.setLayoutY(50);
                   grupo1.setLayoutX(50);
                   grupo2.setLayoutY(50);
                   grupo2.setLayoutX(100);
                   grupo3.setLayoutY(50);
                   grupo3.setLayoutX(150);
                   grupo4.setLayoutY(50);
                   grupo4.setLayoutX(200);
                   finalizado = 0;
                }

////////////////Pico de la nave
            Shape.intersect(circlemeteorito, cabeza);
                Shape ColisionMeteorito2 = Shape.intersect(circlemeteorito, cabeza);
                boolean colisionMeteorito2 = ColisionMeteorito2.getBoundsInLocal().isEmpty();
                if (colisionMeteorito2 == false) {
                   puntuacion = 0;
                   textScore.setText(String.valueOf(puntuacion));
                   grupo1.setLayoutY(50);
                   grupo1.setLayoutX(50);
                   grupo2.setLayoutY(50);
                   grupo2.setLayoutX(100);
                   grupo3.setLayoutY(50);
                   grupo3.setLayoutX(150);
                   grupo4.setLayoutY(50);
                   grupo4.setLayoutX(200);
                   finalizado = 0;
                }

//////////////////////////Colision del meteorito 2///////////////////////////////

////////////////////Ala izquierda
            Shape.intersect(circlemeteorito2, derecha);
                Shape ColisionMeteorito3 = Shape.intersect(circlemeteorito2, derecha);
                boolean colisionMeteorito3 = ColisionMeteorito3.getBoundsInLocal().isEmpty();
                if (colisionMeteorito3 == false) {
                   puntuacion = 0;
                   textScore.setText(String.valueOf(puntuacion));
                   grupo1.setLayoutY(50);
                   grupo1.setLayoutX(50);
                   grupo2.setLayoutY(50);
                   grupo2.setLayoutX(100);
                   grupo3.setLayoutY(50);
                   grupo3.setLayoutX(150);
                   grupo4.setLayoutY(50);
                   grupo4.setLayoutX(200);
                   finalizado = 0;
                }

//////////////////ala derecha
            Shape.intersect(circlemeteorito2, izquierda);
                Shape ColisionMeteorito4 = Shape.intersect(circlemeteorito, izquierda);
                boolean colisionMeteorito4 = ColisionMeteorito4.getBoundsInLocal().isEmpty();
                if (colisionMeteorito4== false) {
                   puntuacion = 0;
                   textScore.setText(String.valueOf(puntuacion));
                   grupo1.setLayoutY(50);
                   grupo1.setLayoutX(50);
                   grupo2.setLayoutY(50);
                   grupo2.setLayoutX(100);
                   grupo3.setLayoutY(50);
                   grupo3.setLayoutX(150);
                   grupo4.setLayoutY(50);
                   grupo4.setLayoutX(200);
                   finalizado = 0;
                }
                 
//////////////////Pico de la nave
            Shape.intersect(circlemeteorito2, izquierda);
                Shape ColisionMeteorito5 = Shape.intersect(circlemeteorito, cabeza);
                boolean colisionMeteorito5 = ColisionMeteorito5.getBoundsInLocal().isEmpty();
                if (colisionMeteorito5== false) {
                   puntuacion = 0;
                   textScore.setText(String.valueOf(puntuacion));
                   grupo1.setLayoutY(50);
                   grupo1.setLayoutX(50);
                   grupo2.setLayoutY(50);
                   grupo2.setLayoutX(100);
                   grupo3.setLayoutY(50);
                   grupo3.setLayoutX(150);
                   grupo4.setLayoutY(50);
                   grupo4.setLayoutX(200);
                   finalizado = 0;
                }
                 
//////////////////////////Colision del meteorito 3///////////////////////////////

////////////////////Ala izquierda
            Shape.intersect(circlemeteorito3, derecha);
                Shape ColisionMeteorito6 = Shape.intersect(circlemeteorito3, derecha);
                boolean colisionMeteorito6 = ColisionMeteorito6.getBoundsInLocal().isEmpty();
                if (colisionMeteorito6 == false) {
                   puntuacion = 0;
                   textScore.setText(String.valueOf(puntuacion));
                   grupo1.setLayoutY(50);
                   grupo1.setLayoutX(50);
                   grupo2.setLayoutY(50);
                   grupo2.setLayoutX(100);
                   grupo3.setLayoutY(50);
                   grupo3.setLayoutX(150);
                   grupo4.setLayoutY(50);
                   grupo4.setLayoutX(200);
                   finalizado = 0;
                }

//////////////////ala derecha
            Shape.intersect(circlemeteorito3, izquierda);
                Shape ColisionMeteorito8 = Shape.intersect(circlemeteorito3, izquierda);
                boolean colisionMeteorito8 = ColisionMeteorito8.getBoundsInLocal().isEmpty();
                if (colisionMeteorito8== false) {
                   puntuacion = 0;
                   textScore.setText(String.valueOf(puntuacion));
                   grupo1.setLayoutY(50);
                   grupo1.setLayoutX(50);
                   grupo2.setLayoutY(50);
                   grupo2.setLayoutX(100);
                   grupo3.setLayoutY(50);
                   grupo3.setLayoutX(150);
                   grupo4.setLayoutY(50);
                   grupo4.setLayoutX(200);
                   finalizado = 0;
                }
                 
//////////////////Pico de la nave
            Shape.intersect(circlemeteorito3, izquierda);
                Shape ColisionMeteorito9 = Shape.intersect(circlemeteorito3, cabeza);
                boolean colisionMeteorito9 = ColisionMeteorito9.getBoundsInLocal().isEmpty();
                if (colisionMeteorito5== false) {
                   puntuacion = 0;
                   textScore.setText(String.valueOf(puntuacion));
                   grupo1.setLayoutY(50);
                   grupo1.setLayoutX(50);
                   grupo2.setLayoutY(50);
                   grupo2.setLayoutX(100);
                   grupo3.setLayoutY(50);
                   grupo3.setLayoutX(150);
                   grupo4.setLayoutY(50);
                   grupo4.setLayoutX(200);
                   finalizado = 0;
                }

            })
               
               

        );

        animationespacio.setCycleCount(Timeline.INDEFINITE);
        animationespacio.play();
    }
   
    public static void main(String[] args) {
        launch();
    }

}