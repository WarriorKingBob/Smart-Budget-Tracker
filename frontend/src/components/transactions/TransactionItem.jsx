function TransactionItem({ transaction }) {
    const { date, category, type, amount, description } = transaction;
    const isIncome = type === 'INCOME';

    return (
        <tr>
            <td>{date}</td>
            <td>{category}</td>
            <td>{isIncome ? amount.toFixed(2) : ''}</td>
            <td>{!isIncome ? amount.toFixed(2) : ''}</td>
            <td>{description}</td>
        </tr>
    );
}

export default TransactionItem;