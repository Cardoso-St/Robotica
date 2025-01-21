package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;



public class Robot extends TimedRobot {

   //motores da direita
   CANSparkMax motorD1 = new CANSparkMax(1, MotorType.kBrushless);
   CANSparkMax motorD2 = new CANSparkMax(2, MotorType.kBrushless);
 
   //motores da esquerda 
   CANSparkMax motorE1 = new CANSparkMax(3, MotorType.kBrushless);
   CANSparkMax motorE2 = new CANSparkMax(4, MotorType.kBrushless);

   //Drivetrain
   DifferentialDrive drive;
 
   //Variavel de velocidade do chassi
   double speed;

   //Motores do Intake
   CANSparkMax Intake1 = new CANSparkMax(5, MotorType.kBrushless); //Sobe e desce o intake
   CANSparkMax Intake2 = new CANSparkMax(6, MotorType.kBrushless); //Recolhe e solta o elemento de jogo

   //Motores do Shotter
   CANSparkMax Shotter1 = new CANSparkMax(7, MotorType.kBrushless); //Lanca o elemento de jogo
   CANSparkMax Shotter2 = new CANSparkMax(8, MotorType.kBrushless); //Lanca o elemento de jogo

   //Controles
   Joystick controle1 = new Joystick(0); //Seta o controle1 na porta USB 0 (lado esquerdo)
   Joystick controle2 = new Joystick(1); //Seta o controle2 na porta USB 1 (Lado direito)
 
  //time para autonomo
   private double startTime;

   //Variaveis autonomos
   boolean centro = true; 
   boolean lado_esquerdo = false;
   boolean lado_direito = false;

  @Override
  public void robotInit() {

    //Inserindo os motores em seus respectivos grupos
    motorD2.follow(motorD1); 
    motorE2.follow(motorE1);
  
    //Setando os grupos de motores no Drivetrain
    drive = new DifferentialDrive(motorD1, motorE1);
    
    //Valor da variavel de velocidade do chassi
    speed = 0.5;

  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
    startTime = Timer.getFPGATimestamp();
  }

  @Override
  public void autonomousPeriodic() {
    double time = Timer.getFPGATimestamp();
    
    
    if(centro == true){

    if(time - startTime < 2){ // Liga shotter
      Shotter1.set(1);
      Shotter2.set(-1);
    } else{}

    if(time - startTime <= 3 && time - startTime > 2 ){
      Intake2.set(1);  // Liga intake (lanca a nota no alto falante)
    }else{}

    if(time - startTime >= 4 && time - startTime <=6){
      Intake2.set(-0.2); //Recolher a argola
      Shotter1.stopMotor();  //Para ambos os motores do shotter
      Shotter2.stopMotor();  //Para ambos os motores do shotter
      Intake1.set(-0.6); //Desce o intake para pegar a proxima argola
      motorD1.set(0.22); // movimenta o robo para frente  
      motorE1.set(-0.22);  // movimenta o robo para frente
    }else{}

    if(time - startTime >= 7 && time - startTime <= 10){
     motorD1.set(-0.25); // movimenta para tras
     motorE1.set(0.25); // movimenta para tras
     Intake1.set(0.6);  //Sobe o Intake
     Intake2.stopMotor(); //Parar o intake 
     Shotter1.set(1); //Ligar ambos motores do shotter
     Shotter2.set(-1); //Ligar ambos motores do shotter 
    }else{}

    if(time - startTime >= 11 && time - startTime <= 12){
     Intake2.set(1); 
     Intake1.stopMotor();
    } else {}

    if(time - startTime >= 13 && time - startTime <= 14){
      Intake2.stopMotor(); 
      Shotter1.stopMotor();
      Shotter2.stopMotor();
      Intake1.stopMotor();
      motorD1.stopMotor();
      motorE1.stopMotor();
    }
  }

  if(lado_esquerdo == true){

    if(time - startTime < 2){ // Liga shotter
      Shotter1.set(1);
      Shotter2.set(-1);
    } else{}

    if(time - startTime <= 3 && time - startTime > 2 ){
      Intake2.set(1);  // Liga intake (lanca a nota no alto falante)
    }else{}

    if(time - startTime >= 4 && time - startTime <=6){
      Intake2.stopMotor();; //Recolher a argola
      Shotter1.stopMotor();  //Para ambos os motores do shotter
      Shotter2.stopMotor();  //Para ambos os motores do shotter
      Intake1.stopMotor(); //Desce o intake para pegar a proxima argola
      //motorD1.set(0.2); // movimenta o robo para frente  
      motorE1.set(-0.08);  // movimenta o robo para frente
    }else{}

    if(time - startTime >= 7 && time - startTime <= 11){
     motorD1.set(0.25); // movimenta para frente
     motorE1.set(-0.25); // movimenta para frente
    
    }else{}

    }

     if(lado_direito == true){

    if(time - startTime < 2){ // Liga shotter 
      Shotter1.set(1);
      Shotter2.set(-1);
    } else{}

    if(time - startTime <= 3 && time - startTime > 2 ){
      Intake2.set(1);  // Liga intake (lanca a nota no alto falante)
    }else{}

    if(time - startTime >= 4 && time - startTime <=6){
      Intake2.stopMotor();; //Recolher a argola
      Shotter1.stopMotor();  //Para ambos os motores do shotter
      Shotter2.stopMotor();  //Para ambos os motores do shotter
      Intake1.stopMotor(); //Desce o intake para pegar a proxima argola
      motorD1.set(0.08); // movimenta o robo para frente  
      //motorE1.set(0.2);  // movimenta o robo para frente
    }else{}
  
    if(time - startTime >= 7 && time - startTime <= 11){
     motorD1.set(0.25); // movimenta para frente
     motorE1.set(-0.25); // movimenta para frente
    
    }else{}

     }
    
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {

    //Drivetrain  
    drive.arcadeDrive(controle1.getRawAxis(4)*speed, controle1.getRawAxis(1)*speed);
    
    //Marcha do Drivetrain
    if(controle1.getRawButtonPressed(5)) {
      speed = 0.6;
    } else if (controle1.getRawButtonPressed(6)) {
      speed = 0.9;
    }  
    

    //Intake
    if (controle2.getRawButton(1)) { //Desce o intake
      Intake1.set(-0.3);
    } else if (controle2.getRawButton(4)) {//Sobe o Intake
      Intake1.set(0.3);
    } else {
      Intake1.set(0);
    }

    if (controle2.getRawButton(3)) { //Recolhe o elemento de jogo
      Intake2.set(-0.5);
    } else if (controle2.getRawButton(2)) { //Solta o elemento de jogo
      Intake2.set(1);
    } else {
      Intake2.set(0);
    }

      //Shotter (Lancar no ar-condicionado)
    if (controle2.getRawButton(5)) {
      Shotter1.set(1);
      Shotter2.set(-1);
      //Shotter (Lancar na janela)
    } else if (controle2.getRawButton(6)) {
      Shotter1.set(0.05);
      Shotter2.set(-0.05);
    } else {
      Shotter1.set(0);
      Shotter2.set(0);
    }

      }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
