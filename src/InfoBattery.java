import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;

public class InfoBattery {
    // 2.1
    public double getBatteryPercentage() {
        // 2.2 Se Declara la variable
        double batteryPercentage = 0.0;

        // 2.3 El metodo getBatteryPercentage() inicia un nuevo objeto de tipo PowerShell:
        try (PowerShell powerShell = PowerShell.openSession()) {
            // 2.4 Se guarda el command .EstimatedChargeRemaining el cual se invoca en el PowerShellResponse response:
            String command = "(Get-WmiObject -Class Win32_Battery).EstimatedChargeRemaining";
            // 2.5 Se genera un tipo de dato PowerShellResponse llamado response al cual se iguala con la invocación de la powershell que equivale al cmd o (cmdlets)
            // seguido se le indica imperativamente mediante el comando .executeCommand("(Get-WmiObject -Class Win32_Battery).EstimatedChargeRemaining";);
            PowerShellResponse response = powerShell.executeCommand(command);
            // 2.6 evalua si la condición de response isError()?
            if (response.isError()) {
                System.out.println("Error al ejecutar el comando PowerShell: " + response.getCommandOutput());
            // 2.7 de lo contrario
            } else {
                // 2.8 En la siguiente linea de tipo String output se le asigna el valor obtenido de response.GetComandOutput() mediante el que se obtiene el comando de salida:
                String output = response.getCommandOutput();
                // 2.9 Aquí se parsea el dato a tipo Double
                batteryPercentage = Double.parseDouble(output);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } // 2.10** Y se retorna el dato o valor al main() desde donde se invoco la clase y sus funciones
        return batteryPercentage;
    }
    public static void main(String[] args) {
        // 1.0 Se genera nueva instancia del objeto referente a nuestra clase infoBattery del tipo InfoBattery
        InfoBattery infoBattery = new InfoBattery();
        // 2.0 Invocamos el metodo .getBatteryPercentage() perteneciente a la clase infoBattery
        double percentage = infoBattery.getBatteryPercentage();
        // 2.10** - 3.0**
        System.out.println("Porcentaje de carga de la bateria: " + percentage + "%");
    } //3.0**
} //3.0//

/*
Bien hasta aquí funciona nuestro codigo utilizando la libreria JPowerShell-3.1.1..jar
del profesor Falken, obteniendo y visualizando en porcentaje actual de nuestra batería.
Lo siguiente, será obtener el amperaje y la tensión de voltaje presente y sí actualmente
el suministro de corriente está enchufado o no, en el momento en que se ejecute el software.
Por ahora nada más.
 */