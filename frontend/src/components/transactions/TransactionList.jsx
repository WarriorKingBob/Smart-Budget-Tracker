import TransactionItem from "./TransactionItem";

function TransactionList({ transactions, accounts }) {
    if (transactions.length === 0) return <p>No transactions yet. You should add one. :)</p>;

    return (
        <table>
            <thead>
            <tr>
                <th>Date</th>
                <th>Category</th>
                <th>Income</th>
                <th>Expense</th>
                <th>Description</th>
                <th>Account</th>
            </tr>
            </thead>
            <tbody>
            {transactions.map((t) => (
                <TransactionItem key={t.id} transaction={t} accounts={accounts} />
            ))}
            </tbody>
        </table>
    );
}

export default TransactionList;