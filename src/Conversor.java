import javax.swing.*;
import java.util.ArrayList;

public class Conversor {
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Si Nimbus no está disponible, puedes establecer el L&F a otro que te guste
        }

        while (true) {
            String[] opciones = {"Conversor de Moneda", "Conversor de Temperatura", "Conversor de Kilometraje", "Conversor de Años luz"};

            String seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione una opción", "Conversor",
                    JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

            if (seleccion == null) {
                System.exit(0);
            }

            if (seleccion.equals(opciones[0])) {
                ArrayList<OpcionMoneda> opcionesMoneda = new ArrayList<>();
                opcionesMoneda.add(new OpcionMoneda("Convertir de Pesos Colombianos a Dólar", 1 / 3939.25, "Dólares"));
                opcionesMoneda.add(new OpcionMoneda("Convertir de Pesos Colombianos a Euros", 1 / 4219.74, "Euros"));
                opcionesMoneda.add(new OpcionMoneda("Convertir de Pesos Colombianos a Libras", 0.00021, "Libras"));
                opcionesMoneda.add(new OpcionMoneda("Convertir de Pesos Colombianos a Yen", 0.040, "Yen"));
                opcionesMoneda.add(new OpcionMoneda("Convertir de Pesos Colombianos a Won", 0.34998, "Won"));
                opcionesMoneda.add(new OpcionMoneda("Convertir de Dólar a Pesos Colombianos", 3939.25, "Pesos Colombianos"));
                opcionesMoneda.add(new OpcionMoneda("Convertir de Euros a Pesos Colombianos", 4219.74, "Pesos Colombianos"));
                opcionesMoneda.add(new OpcionMoneda("Convertir de Libras a Pesos Colombianos", 1 / 0.00021, "Pesos Colombianos"));
                opcionesMoneda.add(new OpcionMoneda("Convertir de Yen a Pesos Colombianos", 1 / 0.040, "Pesos Colombianos"));
                opcionesMoneda.add(new OpcionMoneda("Convertir de Won a Pesos Colombianos", 1 / 0.34998, "Pesos Colombianos"));

                OpcionMoneda seleccionMoneda = (OpcionMoneda) JOptionPane.showInputDialog(null, "Seleccione una opción", "Conversor de Moneda",
                        JOptionPane.QUESTION_MESSAGE, null, opcionesMoneda.toArray(), opcionesMoneda.get(0));

                if (seleccionMoneda == null) {
                    System.exit(0);
                }

                double valor = 0;
                boolean esNumeroValido = false;

                do {
                    try {
                        String valorIngresado = JOptionPane.showInputDialog("Ingrese el valor que desea convertir");
                        valor = Double.parseDouble(valorIngresado);
                        esNumeroValido = true;
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } while (!esNumeroValido);

                double tasaDeCambio = seleccionMoneda.getTasaDeCambio();
                double valorConvertido = valor * tasaDeCambio;

                JOptionPane.showMessageDialog(null, "El valor convertido es: " + valorConvertido + " " + seleccionMoneda.getNombreMoneda());

                // Aquí va el código para el conversor de moneda, utilizando el valor ingresado y la selección de moneda
            } else if (seleccion.equals(opciones[1])) {
                ArrayList<OpcionTemperatura> opcionesTemperatura = new ArrayList<>();
                opcionesTemperatura.add(new OpcionTemperatura("Convertir de Celsius a Fahrenheit", "Fahrenheit", 9.0 / 5.0));
                opcionesTemperatura.add(new OpcionTemperatura("Convertir de Fahrenheit a Celsius", "Celsius", 5.0 / 9.0));
                opcionesTemperatura.add(new OpcionTemperatura("Convertir de Celsius a Kelvin", "Kelvin", 1));
                opcionesTemperatura.add(new OpcionTemperatura("Convertir de Kelvin a Celsius", "Celsius", 1));

                OpcionTemperatura seleccionTemperatura = (OpcionTemperatura) JOptionPane.showInputDialog(null, "Seleccione una opción", "Conversor de Temperatura",
                        JOptionPane.QUESTION_MESSAGE, null, opcionesTemperatura.toArray(), opcionesTemperatura.get(0));

                if (seleccionTemperatura == null) {
                    System.exit(0);
                }

                double valor = 0;
                boolean esNumeroValido = false;

                do {
                    try {
                        String valorIngresado = JOptionPane.showInputDialog("Ingrese el valor que desea convertir");
                        valor = Double.parseDouble(valorIngresado);
                        esNumeroValido = true;
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } while (!esNumeroValido);

                double valorConvertido = seleccionTemperatura.convertir(valor);

                JOptionPane.showMessageDialog(null, "El valor convertido es: " + valorConvertido + " " + seleccionTemperatura.getUnidadTemperatura());
            } else if (seleccion.equals(opciones[2])) {
                // Aquí va el código para el conversor de velocidad
            }

            Object[] options = {"Sí", "No", "Cancelar"};
            int respuesta = JOptionPane.showOptionDialog(null,
                    "¿Quieres continuar?",
                    "Conversor",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (respuesta == JOptionPane.YES_OPTION) {
                // Si el usuario selecciona "Sí", vuelve al inicio del ciclo
                continue;
            } else if (respuesta == JOptionPane.NO_OPTION) {
                // Si el usuario selecciona "No", muestra un mensaje y luego termina el programa
                JOptionPane.showMessageDialog(null, "Programa terminado");
                System.exit(0);
            } else {
                // Si el usuario selecciona "Cancelar" o cierra la ventana de diálogo, termina el programa
                System.exit(0);
            }
        }
    }
}

class OpcionMoneda {
    private String descripcion;
    private double tasaDeCambio;
    private String nombreMoneda;

    public OpcionMoneda(String descripcion, double tasaDeCambio, String nombreMoneda) {
        this.descripcion = descripcion;
        this.tasaDeCambio = tasaDeCambio;
        this.nombreMoneda = nombreMoneda;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getTasaDeCambio() {
        return tasaDeCambio;
    }

    public String getNombreMoneda() {
        return nombreMoneda;
    }

    // Sobreescribimos el método toString para que al mostrar el objeto en el JOptionPane, se muestre la descripción
    @Override
    public String toString() {
        return descripcion;
    }
}

class OpcionTemperatura {
    private String descripcion;
    private String unidadTemperatura;
    private double tasaDeCambio;

    public OpcionTemperatura(String descripcion, String unidadTemperatura, double tasaDeCambio) {
        this.descripcion = descripcion;
        this.unidadTemperatura = unidadTemperatura;
        this.tasaDeCambio = tasaDeCambio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getUnidadTemperatura() {
        return unidadTemperatura;
    }

    public double convertir(double valor) {
        return valor * tasaDeCambio;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}