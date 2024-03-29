//  StandardStudy.java
//
//  Authors:
//       Antonio J. Nebro <antonio@lcc.uma.es>
//       Juan J. Durillo <durillo@lcc.uma.es>
//
//  Copyright (c) 2011 Antonio J. Nebro, Juan J. Durillo
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Lesser General Public License for more details.
// 
//  You should have received a copy of the GNU Lesser General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.

package jmetal.experiments;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import jmetal.core.Algorithm;
import jmetal.core.Problem;
import jmetal.experiments.settings.*;
//incluído por Sandra
import jmetal.experiments.util.RBoxplot;
import jmetal.experiments.util.RWilcoxon;
import jmetal.util.JMException;

/**
 * Class implementing a typical experimental study. Five algorithms are 
 * compared when solving the ZDT, DTLZ, and WFG benchmarks, and the hypervolume,
 * spread and additive epsilon indicators are used for performance assessment.
 */
public class StandardStudy_FlowShopDD extends Experiment {

    /**
     * Configures the algorithms in each independent run
     * @param problemName The problem to solve
     * @param problemIndex
     * @throws ClassNotFoundException
     */
    public void algorithmSettings(String problemName,
                                  int problemIndex,
                                  Algorithm[] algorithm) throws ClassNotFoundException {
        try {
            int numberOfAlgorithms = algorithmNameList_.length;

            HashMap[] parameters = new HashMap[numberOfAlgorithms];

            for (int i = 0; i < numberOfAlgorithms; i++) {
                parameters[i] = new HashMap();
            } // for

            if (!paretoFrontFile_[problemIndex].equals("")) {
                for (int i = 0; i < numberOfAlgorithms; i++)
                    parameters[i].put("paretoFrontFile_", paretoFrontFile_[problemIndex]);
            } // if

            //algorithm[0] = new NSGAII_Settings(problemName).configure(parameters[0]);
            //algorithm[1] = new SPEA2_Settings(problemName).configure(parameters[1]);
            //algorithm[2] = new MOCell_Settings(problemName).configure(parameters[2]);
            //algorithm[3] = new SMPSO_Settings(problemName).configure(parameters[3]);
            //algorithm[4] = new GDE3_Settings(problemName).configure(parameters[4]);
            //incluído

            //algorithm[0] = new NSGAII_Settings_FlowShopDD(problemName).configure(parameters[0]);

            //algorithm[0] = new NSGAII_Settings_FlowShopDD_with_Local_Search(problemName).configure(parameters[0]);
            //algorithm[1] = new NSGAII_Settings_FlowShopDD_with_Local_Search(problemName).configure(parameters[1]);
            //algorithm[2] = new NSGAII_Settings_FlowShopDD_with_Local_Search(problemName).configure(parameters[2]);
            //algorithm[3] = new NSGAII_Settings_FlowShopDD_with_Local_Search(problemName).configure(parameters[3]);
            //Gambiarra que eu fiz pq n consegui pensar em nada melhor



            HashMap  pulicParameters;
            int[] localSearchFrequency;
            int[] proibitionRules;
            Settings aux;
            /*Pra passar os parâmetros direto pra classe de Settings,
              já que n sei como que esses parameters funcionam.*/

            /*-------------------------------------*/
            //Alg 1
            pulicParameters = new HashMap();
            localSearchFrequency = new int[1];
            localSearchFrequency[0] = 100;
            pulicParameters.put("localSearchFrequency", localSearchFrequency);

            proibitionRules = new int[1];
            proibitionRules[0] = 2;
            pulicParameters.put("proibitionRules", proibitionRules);

            aux = new NSGAII_Settings_FlowShopDD_with_Local_Search(problemName);
            ((NSGAII_Settings_FlowShopDD_with_Local_Search) aux).publicParameters = pulicParameters;
            algorithm[0] = aux.configure(parameters[0]);

            /*-----------------------------------------*/
            //Alg 2
            pulicParameters = new HashMap();
            localSearchFrequency = new int[1];
            localSearchFrequency[0] = 30;
            pulicParameters.put("localSearchFrequency", localSearchFrequency);

            proibitionRules = new int[1];
            proibitionRules[0] = 2;
            pulicParameters.put("proibitionRules", proibitionRules);

            aux = new NSGAII_Settings_FlowShopDD_with_Local_Search(problemName);
            ((NSGAII_Settings_FlowShopDD_with_Local_Search) aux).publicParameters = pulicParameters;
            algorithm[1] = aux.configure(parameters[1]);
            //-------------------------------------

            //Alg 3
            pulicParameters = new HashMap();
            localSearchFrequency = new int[1];
            localSearchFrequency[0] = 100;
            pulicParameters.put("localSearchFrequency", localSearchFrequency);

            proibitionRules = new int[1];
            proibitionRules[0] = 7;
            pulicParameters.put("proibitionRules", proibitionRules);

            aux = new NSGAII_Settings_FlowShopDD_with_Local_Search(problemName);
            ((NSGAII_Settings_FlowShopDD_with_Local_Search) aux).publicParameters = pulicParameters;
            algorithm[2] = aux.configure(parameters[2]);
            //-------------------------------------
            //Alg 4
            pulicParameters = new HashMap();
            localSearchFrequency = new int[1];
            localSearchFrequency[0] = 30;
            pulicParameters.put("localSearchFrequency", localSearchFrequency);

            proibitionRules = new int[1];
            proibitionRules[0] = 7;
            pulicParameters.put("proibitionRules", proibitionRules);

            aux = new NSGAII_Settings_FlowShopDD_with_Local_Search(problemName);
            ((NSGAII_Settings_FlowShopDD_with_Local_Search) aux).publicParameters = pulicParameters;
            algorithm[3] = aux.configure(parameters[3]);
            //-------------------------------------
             /*
            //Alg 5
            pulicParameters = new HashMap();
            localSearchFrequency = new int[1];
            localSearchFrequency[0] = 30;
            pulicParameters.put("localSearchFrequency", localSearchFrequency);

            proibitionRules = new int[1];
            proibitionRules[0] = 7;
            pulicParameters.put("proibitionRules", proibitionRules);

            aux = new NSGAII_Settings_FlowShopDD_with_Local_Search(problemName);
            ((NSGAII_Settings_FlowShopDD_with_Local_Search) aux).publicParameters = pulicParameters;
            algorithm[4] = aux.configure(parameters[4]);
            //-------------------------------------
            //Alg 6
            pulicParameters = new HashMap();
            localSearchFrequency = new int[1];
            localSearchFrequency[0] = 30;
            pulicParameters.put("localSearchFrequency", localSearchFrequency);

            proibitionRules = new int[2];
            proibitionRules[0] = 2;
            proibitionRules[1] = 7;
            pulicParameters.put("proibitionRules", proibitionRules);

            aux = new NSGAII_Settings_FlowShopDD_with_Local_Search(problemName);
            ((NSGAII_Settings_FlowShopDD_with_Local_Search) aux).publicParameters = pulicParameters;
            algorithm[5] = aux.configure(parameters[5]);
            //-------------------------------------
            */
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(StandardStudy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(StandardStudy.class.getName()).log(Level.SEVERE, null, ex);
        } catch  (JMException ex) {
            Logger.getLogger(StandardStudy.class.getName()).log(Level.SEVERE, null, ex);
        }
    } // algorithmSettings

    /**
     * Main method
     * @param args
     * @throws JMException
     * @throws IOException
     */
    public static void main(String[] args) throws JMException, IOException {
        //StandardStudy exp = new StandardStudy();
        StandardStudy_FlowShopDD exp = new StandardStudy_FlowShopDD();

        exp.experimentName_ = "StandardStudy";  //usado ns tabelas do latex evitar "_"
        exp.algorithmNameList_ = new String[]{"ex3-rul2-freq100","ex3-rul2-freq30","ex3-rul7-freq100","ex3-rul7-freq30"};
        //nome dos algoritmos a serem executados, vão ser criadas pastas e colunas das tabelas com estes nomes
        //"NSGAII", "SPEA2", "MOCell", "SMPSO", "GDE3"};
        //"NSGAIIRul7","NSGAIIRul2","NSGAIIrules7_2", "NSGAIIrules2_7"

        exp.problemList_ = new String[]{"DD_Ta010.txt","DD_Ta020.txt", "DD_Ta030.txt", "DD_Ta040.txt",
                "DD_Ta050.txt",  "DD_Ta060.txt",
                "DD_Ta070.txt", "DD_Ta080.txt", "DD_Ta090.txt",
                "DD_Ta100.txt","DD_Ta110.txt",};  //nomes das instâncias que serão executadas

        exp.paretoFrontFile_ = new String[]{"","","","","","","","","","",""}; //para cada instância um abre e fecha aspas indicando que não temos o arquivo correspondente a fronteira de Pareto


        exp.indicatorList_ = new String[]{"IGD","HV", "SPREAD", "EPSILON"}; //será gerado os valores destes indicadores
        //"HV", "SPREAD", "EPSILON"};

        int numberOfAlgorithms = exp.algorithmNameList_.length;

        exp.experimentBaseDirectory_ = "testes" +exp.experimentName_;  //os resultados estarão aqui nesta pasta
        exp.paretoFrontDirectory_ = "paretoFronts";

        exp.algorithmSettings_ = new Settings[numberOfAlgorithms]; //um vetor contendo os Settings dos algoritmos

        exp.independentRuns_ = 10;

        // Run the experiments
        int numberOfThreads ;
        exp.runExperiment(numberOfThreads = 1) ;

        //exp.generateQualityIndicators();//Usar pra gerar os indicadores quando der erro ou algo assim
        //exp.generateReferenceFronts();//Usar quando já tiver os arquivos e precisar das rf


        // Generate latex tables
        exp.generateLatexTables() ;

        // Configure the R scripts to be generated
        int rows  ;
        int columns  ;
        String prefix ;
        String [] problems ;
        boolean notch ;

        // Configuring scripts for ZDT
        rows = 3 ;
        columns = 2 ;
        prefix = new String("ZDT");
        problems = new String[]{"ZDT1", "ZDT2","ZDT3", "ZDT4","ZDT6"} ;

        exp.generateRBoxplotScripts(rows, columns, problems, prefix, notch = false, exp) ;
        exp.generateRWilcoxonScripts(problems, prefix, exp) ;

        // Configure scripts for DTLZ
        rows = 3 ;
        columns = 3 ;
        prefix = new String("DTLZ");
        problems = new String[]{"DTLZ1","DTLZ2","DTLZ3","DTLZ4","DTLZ5",
                "DTLZ6","DTLZ7"} ;

        exp.generateRBoxplotScripts(rows, columns, problems, prefix, notch=false, exp) ;
        exp.generateRWilcoxonScripts(problems, prefix, exp) ;

        // Configure scripts for WFG
        rows = 3 ;
        columns = 3 ;
        prefix = new String("WFG");
        problems = new String[]{"WFG1","WFG2","WFG3","WFG4","WFG5","WFG6",
                "WFG7","WFG8","WFG9"} ;

        exp.generateRBoxplotScripts(rows, columns, problems, prefix, notch=false, exp) ;
        exp.generateRWilcoxonScripts(problems, prefix, exp) ;
    } // main
} // StandardStudy


