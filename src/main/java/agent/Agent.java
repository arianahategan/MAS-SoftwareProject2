package agent;

/**
 * An abstract software agent class. The agent must be managed by the Simulation
 * class
 */
public abstract class Agent {

	protected int agentX;
	protected int agentY;
	protected int agentDir;
	protected int agentID;

	public Agent(int x, int y, int dir, int id){ agentX=x; agentY=y; agentDir=dir; agentID=id;}


	/**
	 * Provides a Percept to the agent. If the agent has internal state, this
	 * method should update it.
	 */
	public abstract void see(Percept p);

	/**
	 * Have the agent select its next action to perform. In an agent with
	 * internal state, this implements the action: I -> A function.
	 */
	public abstract Action selectAction();

	public abstract void Start();

	/** Returns the agent's X position. */
	public abstract int getAgentX();

	/** Returns the agent's Y position. */
	public abstract int getAgentY();

	/**
	 * Returns the agent's orientation. Note that the constants in the Direction
	 * class are used to interpret the meaning of this result.
	 */
	public abstract int getAgentDir();

	/** Returns the agent's ID. */
	public abstract int getAgentID();

	/** Returns a string the represents the agent's orientation. */
	public abstract String getAgentDirString();

	/** Changes the agent's X position. */
	public abstract void setAgentX(int x);

	/** Changes the agent's Y position. */
	public abstract void setAgentY(int y);

	/** Changes the agent's orientation. */
	public abstract void setAgentDir(int dir);
}
