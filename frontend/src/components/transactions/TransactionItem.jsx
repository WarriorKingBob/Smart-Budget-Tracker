import { formatAccountLabel } from '../../utils/formatAccountLabel';

function TransactionItem({ transaction, accounts }) {
    const { date, category, type, amount, description, accountId } = transaction;
    const isIncome = type === 'INCOME';
    const account = accounts.find((acc) => acc.id === accountId);

    return (
        <tr>
            <td>{date}</td>
            <td>{category}</td>
            <td>{isIncome ? amount.toFixed(2) : ''}</td>
            <td>{!isIncome ? amount.toFixed(2) : ''}</td>
            <td>{description}</td>
            <td>{account ? formatAccountLabel(account) : 'Unknown'}</td>
        </tr>
    );
}

export default TransactionItem;