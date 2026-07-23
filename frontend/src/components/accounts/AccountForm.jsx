import {useState} from "react";

function AccountForm({ onSubmit, initialValues }) {
    const [name, setName] = useState(initialValues?.name ?? '');
    const [type, setType] = useState(initialValues?.type ?? 'CHECKING');
    const [balance, setBalance] = useState(initialValues?.balance ?? '');

    function handleSubmit(e) {
        e.preventDefault();
        onSubmit({ name, type, balance: parseFloat(balance) });
    }

    return (
        <form onSubmit={handleSubmit}>
            <label>
                Bank Name:
                <input type="text" value={name} onChange={(e) => setName(e.target.value)} required />
            </label>

            <label>
                Type:
                <select value={type} onChange={(e) => setType(e.target.value)}>
                    <option value="CHEQUING">Checking</option>
                    <option value="SAVINGS">Savings</option>
                    <option value="CREDIT">Credit Card</option>
                    <option value="INVESTMENT">Investment</option>
                </select>
            </label>

            <label>
                Starting Balance:
                <input type="number" step="0.01" value={balance} onChange={(e) => setBalance(e.target.value)} required />
            </label>

            <label>
                Currency:
                <select value={type} onChange={(e) => setType(e.target.value)}>
                    <option value="CAD">CAD</option>
                    <option value="USD">USD</option>
                    <option value="EUR">EUR</option>
                    <option value="GBP">GBP</option>
                    <option value="RMB">RMB</option>
                </select>
            </label>

            <label>
                Account Opened At (Optional):
                <input
                    type="date"
                    value={date}
                    onChange={(e) => setDate(e.target.value)}
                    required
                />
            </label>

            <button type="submit">Save</button>
        </form>
    );
}

export default AccountForm;