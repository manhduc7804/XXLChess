// public class Reference {
//     package XXLChess;

// import java.util.Timer;
// import java.util.TimerTask;
// import XXLChess.*;

// public class chessClock {
//     private Timer timer1;
//     private Timer timer2;
//     private int player1Time;
//     private int player2Time;



//     public chessClock(int initialTime1, int initialTime2) {
//         timer1 = new Timer();
//         timer2 = new Timer();
//         player1Time = initialTime1;
//         player2Time = initialTime2;
//     }

//     public void startPlayer1Timer() {
//         timer1.scheduleAtFixedRate(new TimerTask() {
//             public void run() {
//                 player1Time--;
//                 if (player1Time == 0) {
//                     timer1.cancel();
//                 }
//             }
//         }, 0, 1000); // repeat every second
//     }

//     public void startPlayer2Timer() {
//         timer2.scheduleAtFixedRate(new TimerTask() {
//             public void run() {
//                 player2Time--;
//                 if (player2Time == 0) {
//                     timer2.cancel();
//                 }
//             }
//         }, 0, 1000); // repeat every second
//     }

//     public void stopPlayer1Timer() {
//         timer1.cancel();
//         timer1 = new Timer();
//         player1Time += App.IncrementTime1;

//     }

//     public void stopPlayer2Timer() {
//         timer2.cancel();
//         timer2 = new Timer();
//         player2Time += App.IncrementTime2;
        
//     }

//     public void cancelPlayer1Timer() {
//         timer1.cancel();
        
//     }

//     public void cancelPlayer2Timer() {
//         timer2.cancel();
        
//     }


//     public int getPlayer1Time() {
//         return player1Time;
//     }

//     public int getPlayer2Time() {
//         return player2Time;
//     }
// }
// }
