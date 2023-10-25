package br.com.jokenpo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView voce;
    TextView cpu;



    int pontosVoce = 0;
    int pontosCpu = 0;
    int rodada = 0;
    final int maxRodadas = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        voce = findViewById(R.id.voce);
        cpu = findViewById(R.id.cpu);


    }

    public void selecionadoPedra(View view) {
        this.opcaoSelecionada("pedra");
    }

    public void selecionadoPapel(View view) {
        this.opcaoSelecionada("papel");
    }

    public void selecionadoTesoura(View view) {
        this.opcaoSelecionada("tesoura");
    }

    public void opcaoSelecionada(String opcaoSelecionada) {
        TextView textoResultado = findViewById(R.id.textoResultado);

        ImageView imageResultado = findViewById(R.id.imageResultado);
        ImageView ganhou = findViewById(R.id.ganhou);
        ImageView perdeu = findViewById(R.id.perdeu);
        ImageView empate = findViewById(R.id.empate);
        int numero = new Random().nextInt(3);
        String[] opcoes = {"pedra", "papel", "tesoura"};
        String opcaoApp = opcoes[numero];

        switch (opcaoApp) {
            case "pedra":
                imageResultado.setImageResource(R.drawable.pedra);
                break;

            case "papel":
                imageResultado.setImageResource(R.drawable.papel);
                break;

            case "tesoura":
                imageResultado.setImageResource(R.drawable.tesoura);
                break;
        }

        if (
                (opcaoApp.equals("tesoura") && opcaoSelecionada.equals("papel")) ||
                        (opcaoApp.equals("papel") && opcaoSelecionada.equals("pedra")) ||
                        (opcaoApp.equals("pedra") && opcaoSelecionada.equals("tesoura"))
        ) {
            pontosCpu++;
            perdeu.setImageResource(R.drawable.perdeu);
            ganhou.setImageResource(0);
            empate.setImageResource(0);
            textoResultado.setText("Você Perdeu ...");
        } else if (
                (opcaoSelecionada.equals("tesoura") && opcaoApp.equals("papel")) ||
                        (opcaoSelecionada.equals("papel") && opcaoApp.equals("pedra")) ||
                        (opcaoSelecionada.equals("pedra") && opcaoApp.equals("tesoura"))
        ) {
            pontosVoce++;
            ganhou.setImageResource(R.drawable.ganhou);
            textoResultado.setText("Você Ganhou!");
            perdeu.setImageResource(0);
            empate.setImageResource(0);
        } else {
            empate.setImageResource(R.drawable.empatou);
            textoResultado.setText("Empate!");
            ganhou.setImageResource(0);
            perdeu.setImageResource(0);
        }

        rodada++;

        if (rodada == maxRodadas) {
            if (pontosVoce > pontosCpu) {
                textoResultado.setText("Você venceu o melhor de 3!");
            } else if (pontosCpu > pontosVoce) {
                textoResultado.setText("Você perdeu o melhor de 3!");
            } else {
                textoResultado.setText("Empate no melhor de 3!");
            }

            // Reinicie o jogo
            pontosVoce = 0;
            pontosCpu = 0;
            rodada = 0;
        }

        voce.setText("Você: " + pontosVoce);
        cpu.setText("Cpu: " + pontosCpu);
    }
}
