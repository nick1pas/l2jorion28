/*
 * L2jOrion Project - www.l2jorion.com 
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package l2jorion.game.skills.effects;

import l2jorion.game.ai.CtrlIntention;
import l2jorion.game.model.L2Effect;
import l2jorion.game.model.actor.instance.L2PlayableInstance;
import l2jorion.game.model.actor.instance.L2SiegeSummonInstance;
import l2jorion.game.network.serverpackets.MyTargetSelected;
import l2jorion.game.skills.Env;

public class EffectTargetMe extends L2Effect
{
	public EffectTargetMe(final Env env, final EffectTemplate template)
	{
		super(env, template);
	}
	
	@Override
	public EffectType getEffectType()
	{
		return EffectType.TARGET_ME;
	}
	
	@Override
	public void onStart()
	{
		if (getEffected() instanceof L2PlayableInstance)
		{
			if (getEffected() instanceof L2SiegeSummonInstance)
			{
				return;
			}
			
			if (getEffected().getTarget() == getEffector())
			{
				getEffected().getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, getEffector());
			}
			else
			{
				getEffected().setTarget(getEffector());
				getEffected().sendPacket(new MyTargetSelected(getEffector().getObjectId(), 0));
			}
		}
	}
	
	@Override
	public void onExit()
	{
	}
	
	@Override
	public boolean onActionTime()
	{
		return false;
	}
}
