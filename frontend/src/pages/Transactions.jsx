import { useState } from 'react';
import TransactionList from '../components/transactions/TransactionList';
import TransactionForm from '../components/transactions/TransactionForm';
import { mockTransactions } from '../mocks/transactions';
import { mockAccounts } from "../mocks/accounts";

function Transactions() {
    const [transactions, setTransactions] = useState(mockTransactions);
    const [accounts] = useState(mockAccounts);

    function handleAddTransaction(newTransaction) {
        const transactionWithId = { ...newTransaction, id: Date.now() };
        setTransactions([...transactions, transactionWithId]);
    }

    return (
        <div>
            <h1>Transactions</h1>
            <TransactionForm accounts={accounts} onSubmit={handleAddTransaction} />
            <TransactionList transactions={transactions} accounts={accounts} />
        </div>
    );
}

export default Transactions;