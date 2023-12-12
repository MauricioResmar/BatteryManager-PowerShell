import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;

/**
 * @author Mauricio Restrepo
 */
public class GetWin32BatteryInfo {

    /**
     * la función getBatteryPercentage() obtiene, el porcentaje actual de la bateria del equipo mediante PowerShell de Windows y lo retorna como un tipo de dato Double.
     * @return getBatteryPercentage()
     */
    public double getBatteryPercentage() {
        double batteryPercentage = 0.0;
        try (PowerShell powerShell = PowerShell.openSession()){
            String command = "(Get-WmiObject -Class Win32_Battery).EstimatedChargeRemaining";
            PowerShellResponse response = powerShell.executeCommand(command);
            if (response.isError()){
                System.out.println("Error al ejecutar el comando Power Shell: " + response.getCommandOutput());
            } else {
                String output = response.getCommandOutput();
                batteryPercentage = Double.parseDouble(output);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  batteryPercentage;
    }
    public int getBatteryMaxCapacity() {
        int maxCapacity = 0;
        try (PowerShell powerShell = PowerShell.openSession()){
            String command = "(Get-WmiObject -Class Win32_Battery).MaxCapacity";
            PowerShellResponse response = powerShell.executeCommand(command);
            if (response.isError()) {
                System.out.println("Error al ejecutar el comando PowerShell" + response.getCommandOutput());
            } else {
                String output = response.getCommandOutput();
                maxCapacity = Integer.parseInt(output);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return maxCapacity;
    }
    public String getStatus(){
        String status = "";
        try (PowerShell powerShell = PowerShell.openSession()) {
            String command = "(Get-WmiObject -Class Win32_Battery).status";
            PowerShellResponse response = powerShell.executeCommand(command);
            if (response.isError()) {
                System.out.println("Error al ejecutar el comando PowerShell" + response.getCommandOutput());
            } else {
                status = response.getCommandOutput();
            }
        }
        return status;
    }
    public int getBatteryStatus (){
        int batteryStatus = 0;
        try(PowerShell powerShell = PowerShell.openSession()) {
            String command = "(Get-WmiObject -Class Win32_Battery).batteryStatus";
            PowerShellResponse response = powerShell.executeCommand(command);
            if (response.isError()) {
                System.out.println("Error al ejecutar el comando PowerShell" + response.getCommandOutput());
            } else {
                batteryStatus = Integer.parseInt(response.getCommandOutput());
            }
        }
        return batteryStatus;
    }

    public static void main(String[] args) {

        GetWin32BatteryInfo batteryInfo = new GetWin32BatteryInfo();

        double percentage = batteryInfo.getBatteryPercentage();
        System.out.println("Porcentaje de carga de la bateria: " + percentage + "%");

        int maxCapacity = batteryInfo.getBatteryMaxCapacity();
        System.out.println("Capacidad maxima de la bateria: " + maxCapacity + "mAh");

        String statusAc = batteryInfo.getStatus();
        System.out.println("Status actual de la bateria: " + statusAc);

        int batteryStatus = batteryInfo.getBatteryStatus();
        if (batteryStatus == 2) {
            System.out.println("Cargador actualmente disponible y conectado ");
        } else {
            System.out.println("Cargador desconectado");
        }

        System.out.println("El batteryStatus actual es: " + batteryStatus);

    }
}

