/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author Josh Andres
 */
public class Cifrados {

    private static int TAM = 0;

    /**
     * Metodo que pide el ingreso del numero de columnas para el cifrado del
     * mensaje,y su validacion correspondiente donde solo debe de recibir
     * numeros enteros y un numero de columnas mayor o igual a 1.
     */
    public void num_Columnas() {
        String tam_matriz;
        boolean valida = false;
        do {
            try {
                tam_matriz = JOptionPane.showInputDialog(null, "Ingrese el numero de columnas de la matriz:", "Numero de columnas<------------>",
                        JOptionPane.INFORMATION_MESSAGE);
                TAM = Integer.parseInt(tam_matriz);
                if (TAM <= 0) {
                    valida = false;
                    JOptionPane.showMessageDialog(null, "Opcion no valida, numero de filas debe de ser >=1", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    valida = true;
                }

            } catch (Exception numberException) {
                JOptionPane.showMessageDialog(null, "Asegurece de solo ingresar numeros:", "Error al leer el tamaño de la matriz",
                        JOptionPane.ERROR_MESSAGE);
            }
        } while (valida != true);

        if (valida == true) {
            creacion_Matriz_Transposicion(TAM);
        }
    }// fin del metodo que se encarga de filtrar el tamano de la matriz

    /**
     * Metodo que se encarga de recibir el mensaje a cifrar por parte del
     * usuario, retorna un error si el mensaje es mayor a la cantidad de
     * espacios de la matriz y si el mensaje es mas corto llena los espacios
     * restantes con u a 'x'.
     */
    private void creacion_Matriz_Transposicion(int columnas) {
        if (columnas < 100) {
            char matrix[][] = new char[columnas][columnas];
            String mensaje;
            int contador = 0;
            mensaje = JOptionPane.showInputDialog(null, "Ingrese el mensaje que decea cifrar:", "Mensaje a Cifrar:<------------->", JOptionPane.INFORMATION_MESSAGE);
            if (!mensaje.equals("")) {
                char mnsj[] = new char[mensaje.length()];

                for (int i = 0; i < mensaje.length(); i++) {
                    mnsj[i] = mensaje.charAt(i);
                }

                if (mnsj.length > columnas * columnas) {
                    JOptionPane.showMessageDialog(null, "El tamaño de el mensaje es mayor que la capacidad de la matriz", "Error al ingresar los datos",
                            JOptionPane.ERROR_MESSAGE);
                    num_Columnas();

                } else {
                    for (int i = 0; i < columnas; i++) {
                        for (int j = 0; j < columnas; j++) {
                            if (contador < mnsj.length) {
                                matrix[i][j] = mnsj[contador];
                                contador++;
                            } else {
                                matrix[i][j] = 'x';
                            }

                        }

                    }

                }

                for (int i = 0; i < columnas; i++) {
                    System.out.println("\n");
                    for (int j = 0; j < columnas; j++) {
                        System.out.print("\t" + matrix[i][j]);
                    }
                }
                System.out.println("\n\n");
                ingresa_Clave(matrix);
            } else {
                JOptionPane.showMessageDialog(null, "El mensaje debe de contener un caracter como minimo.", "ERROR!!", JOptionPane.ERROR_MESSAGE);
                creacion_Matriz_Transposicion(columnas);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Numero excedido", "Error", JOptionPane.ERROR_MESSAGE);
            num_Columnas();
        }

    }//fin del metodo que crea la matriz

    /**
     * Metodo que pide el ingreso de la clave con la cual se cifrara el mensaje,
     * se debe de ingresar la llave con un espacio entre cada numero, retorna
     * error al no dejar el espacio y al ingresar un numero de columna no
     * existente al igual de la omision de alguna.
     */
    private void ingresa_Clave(char matrix[][]) {
        ////////////////////////////////*************************************////////////////////////////////
        String clave;

        clave = JOptionPane.showInputDialog(null, "Ingrese la clave con llave con la que decea cifrar la informacion:\n"
                + "ejemplo: 0_3_2_4_1 ->para un numero de filas=5;    _ = espacio en blanco", "Clave Para Cifrar:<------------>",
                JOptionPane.INFORMATION_MESSAGE);
        if (!clave.equals("")) {
            ArrayList<String> array = new <String> ArrayList();
            StringTokenizer string = new StringTokenizer(clave);
            while (string.hasMoreTokens()) {
                String linea = string.nextToken();
                array.add(linea);
            }//separacion de la llave en tokens..

            try {
                System.out.print("\nLlave con la cual se cifrará la información:");
                System.out.print(" (");
                for (int i = 0; i < array.size(); i++) {
                    System.out.print("" + array.get(i));
                }
                System.out.print(").");

                int array2[] = new int[array.size()];

                for (int i = 0; i < array2.length; i++) {
                    array2[i] = Integer.parseInt(array.get(i));

                }
                valida_Llave(array2, matrix);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Asegurece de solo ingresar numeros y con un espacio entre ellos.");
                creacion_Matriz_Transposicion(TAM);

            }
        } else {
            JOptionPane.showMessageDialog(null, "No ha ingresado una clave, favor de verificar.", "ERROR!!", JOptionPane.ERROR_MESSAGE);
            ingresa_Clave(matrix);
        }
    }// fin del metodo donde se ingresa la clave que el usuario desea cifrar.

    /**
     * metodo que se encarga de validar la llave con la que será cifrado el
     * mensaje
     */
    private void valida_Llave(int llave[], char matrix[][]) {

        for (int i = 0; i < llave.length; i++) {
            if (llave[i] > TAM) {
                JOptionPane.showMessageDialog(null, "El numero de columna: " + llave[i] + " --> no existe", "Favor de ingresar una columna valida",
                        JOptionPane.ERROR_MESSAGE);
                ingresa_Clave(matrix);
                break;
            } else if (llave.length > TAM) {
                JOptionPane.showMessageDialog(null, "Numero de columnas excedido", "Favor de ingresar una llave valida:",
                        JOptionPane.ERROR_MESSAGE);
                ingresa_Clave(matrix);
                break;
            } else if (llave.length < TAM) {
                JOptionPane.showMessageDialog(null, "Falta Alguna columna", "Favor de ingresar una llave valida:",
                        JOptionPane.ERROR_MESSAGE);
                ingresa_Clave(matrix);
                break;
            } else if (llave.length == TAM) {
                valida_Columnas_Llave(llave, matrix);
                break;

            }
        }

    }//fin del metodo que valida la llave de entrada

    /**
     * Metodo que valida las columnas con que son ingresadas como llave.
     */
    private void valida_Columnas_Llave(int llave[], char matrix[][]) {
        int aux[] = new int[llave.length];//asignacion de tamaño no utilizada.. la siguiente linea de asigacion le da el tamaño por default.
        aux = llave;
        int cont = 0;
        for (int j = 0; j < llave.length; j++) {

            for (int k = 0; k < llave.length; k++) {

                if (aux[j] == llave[k]) {
                    cont++;

                }
            }
        }
        if (cont > llave.length) {
            JOptionPane.showMessageDialog(null, "El numero de una columna se encuentra repetido:",
                    "Error de columna repetida", JOptionPane.ERROR_MESSAGE);
            ingresa_Clave(matrix);
        } else {
            cifrado(matrix, llave);
        }
    }// fin del metodo que valida que no se encuentre el numero de alguna columna repetido.

    /**
     * Metodo donde se cifra el texto introducido por el usuario.
     */
    private void cifrado(char matrix[][], int llave[]) {
        int temporal;
        int cont = 0, cont2 = 0;
        char matrix2[][] = new char[matrix.length][matrix.length];
        System.out.println("\n\nReacomodamiento de la Matriz usando la llave de cifrado (" + Arrays.toString(llave) + ").");
        for (int i = 0; i < matrix2.length; i++) {

            for (int j = 0; j < matrix2.length; j++) {
                temporal = llave[cont];
                matrix2[i][j] = matrix[i][temporal];
                cont++;
            }
            cont = 0;
        }

        for (int k = 0; k < matrix2.length; k++) {
            System.out.println("\t");
            for (int l = 0; l < matrix2.length; l++) {
                System.out.print("  " + matrix2[k][l]);
            }
        }
        System.out.println("\n\n");
        cont = 0;
        System.out.println("<-----------------------El mensaje cifrado es el siguiente--------------------------------------->");
        for (int i = 0; i < matrix2.length; i++) {
            for (int j = 0; j < matrix2.length; j++) {
                temporal = llave[cont];
                System.out.print("" + matrix[cont2][temporal]);
                cont2++;
            }
            cont2 = 0;
            cont++;
        }
        System.out.println("\n\n");
//solo debug
//        System.out.println("<---------------------------Mensaje Inicial---------------------------->");
//        cont=0;
//        cont2=0;
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix.length; j++) {
//                temporal=llave[cont];
//                matrix2[i][j]=matrix[i][temporal];
//                cont++;
//
//            }cont=0;
//        }
//        for (int k = 0; k < matrix2.length; k++) {
////              System.out.println("\t");
//            for (int l = 0; l <matrix2.length; l++) {
//                if(matrix[k][l]!='x'){
//                System.out.print("  "+matrix[k][l]);
//                }
//            }
//        }System.out.println("\n\n");
        decifrar(llave, matrix, matrix2);

    }//metodo que cifra el mensaje

    /**
     * Metodo que decifra el mensaje que es introducido por el usuario, despues
     * de haber pedido la llave y compararla con la ingresada anteriormente.
     */
    private void decifrar(int llave[], char matrix[][], char matrix2[][]) {

        String llave2;
        int llave3[] = new int[llave.length];
        try {
            llave2 = JOptionPane.showInputDialog(null, "Ingrese la llave para decifrar el mensaje ", "Mensaje----->", JOptionPane.INFORMATION_MESSAGE);
//     
            ArrayList<String> array = new <String> ArrayList();
            StringTokenizer string = new StringTokenizer(llave2);
            while (string.hasMoreTokens()) {
                String linea = string.nextToken();
                array.add(linea);
            }//fin del ciclo while donde se crea la llave

            for (int i = 0; i < array.size(); i++) {
                llave3[i] = Integer.parseInt(array.get(i));

            }//ciclo donde se transforma cada uno de los caracteres 
            if (llave3.length != llave.length) {
                JOptionPane.showMessageDialog(null, "Asegurece de ingresar una llave y que esta sea valida.", "Error", JOptionPane.ERROR_MESSAGE);
                decifrar(llave, matrix, matrix2);
            } else if (llave3.length == llave.length) {
                String llave1 = "", aux_llave1 = "", key_or = "", aux_key = "";

                for (int i = 0; i < llave.length; i++) {
                    llave1 = String.valueOf(llave3[i]);
                    aux_llave1 = aux_llave1.concat(llave1);

                    key_or = String.valueOf(llave[i]);
                    aux_key = aux_key.concat(key_or);
                }

                if (!aux_llave1.equals(aux_key)) {
                    JOptionPane.showMessageDialog(null, "La llave con la que decea decifrar el mensaje no coincide, ingresela de nuevo.");
                    decifrar(llave, matrix, matrix2);
                } else if (aux_llave1.equals("")) {
                    JOptionPane.showMessageDialog(null, "No ingreso ninguna llave..");
                    decifrar(llave, matrix, matrix2);
                } else {
                    System.out.println("<---------------------------Mensaje Inicial---------------------------->");
                    int cont = 0;
                    int cont2 = 0, temporal;

                    for (int y = 0; y < matrix.length; y++) {
                        for (int j = 0; j < matrix.length; j++) {
                            temporal = llave[cont];
                            matrix2[y][j] = matrix[y][temporal];
                            cont++;

                        }
                        cont = 0;
                    }
                    for (int k = 0; k < matrix2.length; k++) {
                        for (int l = 0; l < matrix2.length; l++) {
                            if (matrix[k][l] != 'x') {
                                System.out.print("  " + matrix[k][l]);
                            }
                        }
                    }
                    System.out.println("\n\n");
                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Asegurece de ingresar una llave y que esta sea valida.", "Error", JOptionPane.ERROR_MESSAGE);
            decifrar(llave, matrix, matrix2);
        }

    }//fin del metodo decifrar---------------------------->

}//fin de la clase

