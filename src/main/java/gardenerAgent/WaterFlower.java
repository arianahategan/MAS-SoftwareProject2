package gardenerAgent;

import agent.Action;
import agent.Agent;
import agent.State;

/**
 * A garden world action that causes the agent to water a flower at the
 * current location.
 */
public class WaterFlower extends Action {

	public WaterFlower() {

	}

	/**
	 * Returns the state that results from the agent watering a flower in the given
	 * state. In order to avoid creating unnecessary objects, we do not create a
	 * new state, but instead modify the old one. This would have to changed if
	 * the Environment needs to maintain a history of states.
	 */
	public State execute(Agent a, State s) {

		int x, y;
		GardenerState state = null;

		if (s instanceof GardenerState)
			state = (GardenerState) s;
		else
			System.out
					.println("ERROR - Argument to WaterFlower.execute() is not of type GardenerState");

		x = a.getAgentX();
		y = a.getAgentY();
		assert state != null;
		state.waterFlower(x, y);
		state.numberOfTrials = state.numberOfTrials + 1;
		return state;
	}

	public String toString() {
		return "WATER FLOWER";
	}
}
