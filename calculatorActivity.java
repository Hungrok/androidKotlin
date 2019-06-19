package com.hungrok.mycalcurator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, buttonAdd, buttonSub, buttonDivision,
            buttonMul, button10, buttonC, buttonEqual;
    EditText crunchifyEditText ;
    EditText tempResultText ;
    String[] values = {"c","c","c","c","c","c","c","c","c","c","c","c"};

    int index = 0 ;
    float tempValue = 0 , resultValue=0 ;
    static final int OPE_INIT = 0 ;
    static final int OPE_ADD = 1 ;
    static final int OPE_SUB = 2 ;
    static final int OPE_MUL= 3 ;
    static final int OPE_DIV = 4 ;
    static final int OPE_MOD = 5 ;
    static final int OPE_RESULT = 6 ;
    int prevOpearator = OPE_INIT ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        button10 = (Button) findViewById(R.id.button10);
        buttonAdd = (Button) findViewById(R.id.buttonadd);
        buttonSub = (Button) findViewById(R.id.buttonsub);
        buttonMul = (Button) findViewById(R.id.buttonmul);
        buttonDivision = (Button) findViewById(R.id.buttondiv);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonEqual = (Button) findViewById(R.id.buttoneql);
        crunchifyEditText = (EditText) findViewById(R.id.edt1);
        tempResultText = (EditText) findViewById(R.id.edt2);

        crunchifyEditText.setText("") ;
        System.out.printf("INIT values=%s, getText=%s \n",values[index],crunchifyEditText.getText()) ;

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((values[index] == "c")&&(resultValue==0))
                    crunchifyEditText.setText("");

                crunchifyEditText.setText(crunchifyEditText.getText() + "1");
                if (values[index] == "c") {
                    values[index] = "1";
                }
                else
                    values[index] = values[index] + "1" ;



            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((values[index] == "c")&&(resultValue==0))
                    crunchifyEditText.setText("");

                crunchifyEditText.setText(crunchifyEditText.getText() + "2");
                if (values[index] == "c")
                    values[index] = "2" ;
                else
                    values[index] = values[index] + "2"

                ;
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((values[index] == "c")&&(resultValue==0))
                    crunchifyEditText.setText("");

                crunchifyEditText.setText(crunchifyEditText.getText() + "3");
                if (values[index] == "c")
                    values[index] = "3" ;
                else
                    values[index] = values[index] + "3" ;
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((values[index] == "c")&&(resultValue==0))
                    crunchifyEditText.setText("");

                crunchifyEditText.setText(crunchifyEditText.getText() + "4");
                if (values[index] == "c")
                    values[index] = "4" ;
                else
                    values[index] = values[index] + "4" ;

            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((values[index] == "c")&&(resultValue==0))
                    crunchifyEditText.setText("");

                crunchifyEditText.setText(crunchifyEditText.getText() + "5");
                if (values[index] == "c")
                    values[index] = "5" ;
                else
                    values[index] = values[index] + "5" ;

            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((values[index] == "c")&&(resultValue==0))
                    crunchifyEditText.setText("");

                crunchifyEditText.setText(crunchifyEditText.getText() + "6");
                if (values[index] == "c")
                    values[index] = "6" ;
                else
                    values[index] = values[index] + "6" ;

            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((values[index] == "c")&&(resultValue==0))
                    crunchifyEditText.setText("");

                crunchifyEditText.setText(crunchifyEditText.getText() + "7");
                if (values[index] == "c")
                    values[index] = "7" ;
                else
                    values[index] = values[index] + "7" ;
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((values[index] == "c")&&(resultValue==0))
                    crunchifyEditText.setText("");

                crunchifyEditText.setText(crunchifyEditText.getText() + "8");
                if (values[index] == "c")
                    values[index] = "8" ;
                else
                    values[index] = values[index] + "8" ;
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((values[index] == "c")&&(resultValue==0))
                    crunchifyEditText.setText("");

                crunchifyEditText.setText(crunchifyEditText.getText() + "9");
                if (values[index] == "c")
                    values[index] = "9" ;
                else
                    values[index] = values[index] + "9" ;
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((values[index] == "c")&&(resultValue==0))
                    crunchifyEditText.setText("");

                crunchifyEditText.setText(crunchifyEditText.getText() + "0");
                if (values[index] == "c")
                    values[index] = "0" ;
                else
                    values[index] = values[index] + "0" ;
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((values[index] == "c")&&(resultValue==0))
                    crunchifyEditText.setText("");

                crunchifyEditText.setText(crunchifyEditText.getText() + ".");
                if (values[index] == "c")
                    values[index] = "." ;
                else
                    values[index] = values[index] + "." ;
            }
        });


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                crunchifyEditText.setText(crunchifyEditText.getText() + "+");
                calculation(OPE_ADD);
            }
        });
        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "-");
                calculation(OPE_SUB);
            }
        });
        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "*");
                calculation(OPE_MUL);
            }
        });
        buttonDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "/");
                calculation(OPE_DIV);
            }
        });
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculation(OPE_RESULT) ;
                crunchifyEditText.setText(Float.toString(resultValue)) ;
                tempResultText.setText("");
                initCal() ;
            }
        });
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText("");
                tempResultText.setText("");
                initCal() ;

            }
        });
    }

    private void calculation(int curOpe){
        tempValue = Float.parseFloat(values[index]);


        if(prevOpearator == OPE_ADD ) {
            resultValue = resultValue + tempValue;
        }
        else if (prevOpearator == OPE_SUB){
            resultValue = resultValue - tempValue;
        }
        else if (prevOpearator == OPE_MUL){
            resultValue = resultValue * tempValue;
        }
        else if (prevOpearator == OPE_DIV){
            resultValue = resultValue / tempValue;
        }
        else if (prevOpearator == OPE_INIT) {
            resultValue = tempValue;
        }

        prevOpearator = curOpe ;
        //index++ ;  // not use string array
        values[0] = "c"; // 초기화
        tempResultText.setText(Float.toString(resultValue)) ;
    }
    private void initCal(){
        index = 0 ;
        tempValue = 0 ;
        resultValue = 0 ;
        prevOpearator = OPE_INIT ;
        values[0] = "c"; // 초기화
    }

}

/*
public class MainActivity extends AppCompatActivity {

    Button button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, buttonAdd, buttonSub, buttonDivision,
            buttonMul, button10, buttonC, buttonEqual;
    EditText crunchifyEditText ;
    float mValueOne, mValueTwo;
    boolean crunchifyAddition, mSubtract, crunchifyMultiplication,
            crunchifyDivision;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        button10 = (Button) findViewById(R.id.button10);
        buttonAdd = (Button) findViewById(R.id.buttonadd);
        buttonSub = (Button) findViewById(R.id.buttonsub);
        buttonMul = (Button) findViewById(R.id.buttonmul);
        buttonDivision = (Button) findViewById(R.id.buttondiv);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonEqual = (Button) findViewById(R.id.buttoneql);
        crunchifyEditText = (EditText) findViewById(R.id.edt1);
        tempResultText = (EditText) findViewById(R.id.edt2);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "9");
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "0");
            }
        });



        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (crunchifyEditText == null) {
                    crunchifyEditText.setText("");
                } else {
                    mValueOne = Float.parseFloat(crunchifyEditText.getText() + "");
                    crunchifyAddition = true;
                    crunchifyEditText.setText(null);
                }
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(crunchifyEditText.getText() + "");
                mSubtract = true;
                crunchifyEditText.setText(null);
            }
        });

        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(crunchifyEditText.getText() + "");
                crunchifyMultiplication = true;
                crunchifyEditText.setText(null);
            }
        });

        buttonDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(crunchifyEditText.getText() + "");
                crunchifyDivision = true;
                crunchifyEditText.setText(null);
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueTwo = Float.parseFloat(crunchifyEditText.getText() + "");

                if (crunchifyAddition == true) {
                    crunchifyEditText.setText(mValueOne + mValueTwo + "");
                    crunchifyAddition = false;
                }

                if (mSubtract == true) {
                    crunchifyEditText.setText(mValueOne - mValueTwo + "");
                    mSubtract = false;
                }

                if (crunchifyMultiplication == true) {
                    crunchifyEditText.setText(mValueOne * mValueTwo + "");
                    crunchifyMultiplication = false;
                }

                if (crunchifyDivision == true) {
                    crunchifyEditText.setText(mValueOne / mValueTwo + "");
                    crunchifyDivision = false;
                }
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + ".");
            }
        });
         buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText("");
            }
        });


    }
}
*/
