import React from 'react';

// Simple Card component
export const Card = ({ children, className }) => (
    <div className={`p-4 shadow-lg rounded-lg bg-white ${className}`}>{children}</div>
);
export const CardHeader = ({ children }) => (
    <div className="border-g p-4">{children}</div>
);

export const CardContent = ({ children }) => (
    <div className="p-4">{children}</div>
);

export const CardTitle = ({ children, className }) => (
    <h2 className={`text-2xl font-semibold ${className}`}>{children}</h2>
);

// Simple Button component
export const Button = ({ children, onClick, className, variant = 'outline' }) => {
    const buttonStyles = variant === 'outline' ? 'border border-green-500 text-green-500' : 'bg-green-500 text-white';
    return (
        <button onClick={onClick} className={`px-4 py-2 rounded ${buttonStyles} ${className}`}>
            {children}
        </button>
    );
};

// Simple Badge component
export const Badge = ({ children, className }) => (
    <span className={`px-2 py-1 bg-gray-200 rounded-full text-sm ${className}`}>{children}</span>
);
