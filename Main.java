public class Main {
    public static void main(String[] args) {

        SQLHelper connServer = new SQLHelper();
        connServer.connect("jdbc:sqlserver://192.168.0.37", "iimadmin", "n1mda11M");

        Controller controller = new Controller();



        String query = "SELECT * FROM [Training].[dbo].[StockInfo]";
        String browseQuery = "SELECT ItemName,Hazard FROM [Training].[dbo].[StockInfo] INNER JOIN [Training].[dbo].[LabInv] ON StockInfo.ItemID = LabInv.ItemID"; //need to specify which id, i.e. labid from session
        connServer.select(browseQuery);

        connServer.close();
    }
}
