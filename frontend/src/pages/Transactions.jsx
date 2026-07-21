import { useState } from 'react';
import TransactionList from '../components/transactions/TransactionList';
import TransactionForm from '../components/transactions/TransactionForm';
import { mockTransactions } from '../mocks/transactions';

function Transactions() {
    const [transactions, setTransactions] = useState(mockTransactions);

    function handleAddTransaction(newTransaction) {
        const transactionWithId = { ...newTransaction, id: Date.now() };
        setTransactions([...transactions, transactionWithId]);
    }

    return (
        <div>
            <h1>Transactions</h1>
            <TransactionForm onSubmit={handleAddTransaction} />
            <TransactionList transactions={transactions} />
        </div>
    );
}

export default Transactions;