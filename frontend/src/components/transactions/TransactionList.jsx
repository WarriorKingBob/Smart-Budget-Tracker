import TransactionItem from "./TransactionItem";

function TransactionList({ transactions }) {
    if (transactions.length === 0) return <p>No transactions yet.</p>;

    return (
        <table>
            <thead>
            <tr>
                <th>Date</th>
                <th>Category</th>
                <th>Income</th>
                <th>Expense</th>
                <th>Description</th>
            </tr>
            </thead>
            <tbody>
            {transactions.map((t) => (
                <TransactionItem key={t.id} transaction={t} />
            ))}
            </tbody>
        </table>
    );
}

export default TransactionList;