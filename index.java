public class NFCReaderActivity extends AppCompatActivity {
    private NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc_reader);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        nfcAdapter.enableReaderMode(this, new NfcAdapter.ReaderCallback() {
            @Override
            public void onTagDiscovered(Tag tag) {
                readNFCtag(tag);
            }
        }, NfcAdapter.FLAG_READER_NFC_A, null);
    }

    private void readNFCtag(Tag tag) {
        NdefMessage ndefMessage = NdefMessage.get(tag);
        if (ndefMessage != null) {
            String chargingSettings = ndefMessage.getRecords()[0].getPayload();
            initiateWirelessCharging(chargingSettings);
        }
    }

    private void initiateWirelessCharging(String chargingSettings) {
    }
}
