import AccountItem from "./AccountItem";

function AccountList({ accounts }) {
    if (accounts.length === 0) return <p>No accounts yet. You should add one. :)</p>;

    return (
        <table>
            <thead>
            <tr>
                <th>Bank</th>
                <th>Type</th>
                <th>Balance</th>
                <th>Currency</th>
                <th>Opened</th>
            </tr>
            </thead>
            <tbody>
            {accounts.map((acc) => (
                <AccountItem key={acc.id} account={acc} />
            ))}
            </tbody>
        </table>
    );
}

export default AccountList;