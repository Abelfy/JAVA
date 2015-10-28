package projetBank.fr.banque;

public class DateInvalid extends BanqueException
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public DateInvalid()
	{
	}

	public DateInvalid(String message)
	{
		super(message);
	}

	public DateInvalid(Throwable cause)
	{
		super(cause);
	}

	public DateInvalid(String message, Throwable cause)
	{
		super(message, cause);
	}

	public DateInvalid(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

}